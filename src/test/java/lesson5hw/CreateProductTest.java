package lesson5hw;

import com.github.javafaker.Faker;
import db.dao.ProductsMapper;
import lesson5hw.api.ProductService;
import lesson5hw.dto.Product;
import lesson5hw.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class CreateProductTest {

    static ProductService productService;
    Product product = null;
    Faker faker = new Faker();
    String prodName = faker.food().ingredient();
    long id;

    @BeforeAll
    static void beforeAll() throws IOException {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);


    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(prodName)
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
    }

    @Test
    void createProductInFoodCategoryTest() throws IOException {
        Response<Product> response = productService.createProduct(product)
                .execute();
        id =  response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        db.dao.ProductsMapper productsMapper = session.getMapper(db.dao.ProductsMapper.class);
        db.model.Products list = productsMapper.selectByPrimaryKey(id);

        assertThat(list.getId(), equalTo(id));
        assertThat(list.getTitle(), equalTo(prodName));


    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProduct((int) id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }



}
