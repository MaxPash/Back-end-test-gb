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

public class GetProductsByIDTest {

        static ProductService productService;
        Product product = null;
        Faker faker = new Faker();
        String prodName = faker.food().ingredient();
        int id;

        @BeforeAll
        static void beforeAll() {
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
        void getProductsByIDTest() throws IOException {
            Response<Product> response = productService.createProduct(product)
                    .execute();
            id =  response.body().getId();

            assertThat(response.isSuccessful(), CoreMatchers.is(true));

            Response<Product> response1 = productService.getProductById(id)
                    .execute();

            assertThat(response.isSuccessful(), CoreMatchers.is(true));
            assertThat(response.body().getTitle(), equalTo(prodName));

        }

        @SneakyThrows
        @AfterEach
        void tearDown() {
            Response<ResponseBody> response = productService.deleteProduct(id).execute();
            assertThat(response.isSuccessful(), CoreMatchers.is(true));
        }



}