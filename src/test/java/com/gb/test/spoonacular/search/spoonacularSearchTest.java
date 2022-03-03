package com.gb.test.spoonacular.search;

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

public class spoonacularSearchTest extends AbstractTest {

    private static String API_KEY = "603cef618e52469c8d3591641acd27c6";
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
    @DisplayName("Empty request test")
    protected void testGetRecipesComplexSearchEmpty() throws IOException, JSONException {

        String actual = given()
                .spec(BASE_SPEC)
                .expect()
                .body("totalResults", is(5226))
                .spec(RESPONSE_SPEC)
                .log()
                .body()
                .when()
                .get("recipes/complexSearch")
                .body()
                .asPrettyString();

        String expected = getResourceAsString("expectedEmptyResponse.json");

        JSONAssert.assertEquals(
                expected,
                actual,
                JSONCompareMode.NON_EXTENSIBLE
        );

    }

    @Test
    @DisplayName("Vegetarian pasta test")
    protected void testGetRecipesComplexSearchVegPasta() throws IOException, JSONException {

        String actual = given()
                .spec(BASE_SPEC)
                .param("query", "pasta")
                .param("diet", "vegetarian")
                .expect()
                .spec(RESPONSE_SPEC)
                .log()
                .body()
                .body("totalResults", is(34))
                .when()
                .get("recipes/complexSearch")
                .body()
                .asPrettyString();

        String expected = getResourceAsString("expectedVegPastaResponse.json");

        JSONAssert.assertEquals(
                expected,
                actual,
                JSONCompareMode.NON_EXTENSIBLE
        );

    }

    @Test
    @DisplayName("Greek Salad test")
    protected void testGetRecipesComplexSearchGreekSalad() throws IOException, JSONException {

        String actual = given()
                .spec(BASE_SPEC)
                .param("query", "Salad")
                .param("cuisine", "Greek")
                .param("number", 3)
                .expect()
                .spec(RESPONSE_SPEC)
                .log()
                .body()
                .body("totalResults", is(362))
                .when()
                .get("recipes/complexSearch")
                .body()
                .asPrettyString();

        String expected = getResourceAsString("greekSaladResponse.json");

        JSONAssert.assertEquals(
                expected,
                actual,
                JSONCompareMode.NON_EXTENSIBLE
        );

    }

    @Test
    @DisplayName("Not Found test")
    protected void testGetRecipesComplexSearchNotFound() throws IOException, JSONException {

        String actual = given()
                .spec(BASE_SPEC)
                .param("query", "Salad")
                .param("cuisine", "Greek")
                .param("number", 3)
                .param("diet", "Paleo")
                .expect()
                .spec(RESPONSE_SPEC)
                .log()
                .body()
                .body("totalResults", is(0))
                .when()
                .get("recipes/complexSearch")
                .body()
                .asPrettyString();

        String expected = getResourceAsString("notFoundResponse.json");

        JSONAssert.assertEquals(
                expected,
                actual,
                JSONCompareMode.NON_EXTENSIBLE
        );

    }
}