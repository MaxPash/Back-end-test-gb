package lesson5hw;

import com.github.javafaker.Faker;
import lesson5hw.api.ProductService;
import lesson5hw.dto.Product;
import lesson5hw.utils.RetrofitUtils;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ReturnProductsTest {

    static ProductService productService;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }


    @Test
    void returnProductsTest() throws IOException {

        Response<ResponseBody> response = productService.getProducts()
                .execute();

        assertThat(response.code(), equalTo(500));


    }



}
