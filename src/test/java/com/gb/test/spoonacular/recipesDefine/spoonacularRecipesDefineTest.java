package com.gb.test.spoonacular.recipesDefine;

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
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class spoonacularRecipesDefineTest extends AbstractTest {

    private static String API_KEY = "603cef618e52469c8d3591641acd27c6";
    private static RequestSpecification BASE_SPEC;
    private static ResponseSpecification RESPONSE_SPEC;


    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://api.spoonacular.com";

        BASE_SPEC = new RequestSpecBuilder()
                .addQueryParam("apiKey", API_KEY)
                .log(LogDetail.ALL)
                .build();

        RESPONSE_SPEC = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(2000L))
                .build();
    }

    @Test
    @DisplayName("Define recipe if cuisine is African test")
    protected void testPostRecipesCuisineAfrican() throws IOException, JSONException {

        String actual = given()
                .spec(BASE_SPEC)
                .queryParam("title", "African Bean Soup")
                .log()
                .all()
                .expect()
                .spec(RESPONSE_SPEC)
                .body("cuisine", is("African"))
                .log()
                .body()
                .when()
                .post("/recipes/cuisine")
                .body()
                .asPrettyString();




    }

    @Test
    @DisplayName("Define recipe if cuisine is American test")
    protected void testPostRecipesCuisineAmerican() throws IOException, JSONException {

        String actual = given()
                .spec(BASE_SPEC)
                .queryParam("title", "The Best Chili")
                .log()
                .parameters()
                .expect()
                .spec(RESPONSE_SPEC)
                .body("cuisine", is("American"))
                .log()
                .body()
                .when()
                .post("/recipes/cuisine")
                .body()
                .asPrettyString();




    }
}
