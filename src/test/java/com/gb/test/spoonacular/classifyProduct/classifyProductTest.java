package com.gb.test.spoonacular.classifyProduct;

import com.gb.test.spoonacular.AbstractTest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class classifyProductTest extends AbstractTest {

    private static final String API_KEY = "603cef618e52469c8d3591641acd27c6";
    private static RequestSpecification BASE_SPEC;
    private static ResponseSpecification RESPONSE_SPEC;


    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://api.spoonacular.com";

        BASE_SPEC = new RequestSpecBuilder()
                .addParam("apiKey", API_KEY)
                .log(LogDetail.ALL)
                .build();

        RESPONSE_SPEC = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(2000L))
                .build();
    }

    @Test
    @DisplayName("Add 1 Banana to meal plan")
    protected void AddBananaToMealPlannerTest() throws IOException, JSONException {



        String actual = given()
                .spec(BASE_SPEC)
                .body("addBanana.json")
                .expect()
                .spec(RESPONSE_SPEC)
                .log()
                .body()
                .when()
                .post("/mealplanner/pascovm@gmail.com/items")
                .body()
                .asPrettyString();




    }
}
