package lesson5hw;

import com.github.javafaker.Faker;
import lesson5hw.api.ProductService;
import lesson5hw.dto.Product;
import lesson5hw.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ModifyProductTest {

    static ProductService productService;
    Product product = null;
    Faker faker = new Faker();
    String productName1 = faker.food().ingredient();
    String productName2 = faker.food().ingredient();
    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @Test
    @SneakyThrows
    void modifyProductTest() throws IOException {

        product = new Product ()
                .withTitle(productName1)
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));

        Response<Product> response1 = productService.createProduct(product)
                .execute();
        id = response1.body().getId();
        assertThat(response1.isSuccessful(), CoreMatchers.is(true));

        product = new Product()
                .withId(id)
                .withTitle(productName2)
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));

        Response<Product> response2 = productService.modifyProduct(product)
                .execute();
        assertThat(response2.isSuccessful(), CoreMatchers.is(true));
        assert response2.body() != null;
        assertThat(response2.body().getTitle(), equalTo(productName2));


    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
