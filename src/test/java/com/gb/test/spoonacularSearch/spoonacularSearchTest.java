package com.gb.test.spoonacularSearch;

import com.gb.test.AbstractTest;
import io.restassured.RestAssured;
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

    @Test
    @DisplayName("Empty request test")
    protected void testGetRecipesComplexSearchEmpty() throws IOException, JSONException {

        String actual = given()
                .param("apiKey", API_KEY)
                .log()
                .parameters()
                .expect()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .body("totalResults", is(5226))
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
                .param("apiKey", API_KEY)
                .param("query", "pasta")
                .param("diet", "vegetarian")
                .log()
                .parameters()
                .expect()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .body("totalResults", is(34))
                .log()
                .body()
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
                .param("apiKey", API_KEY)
                .param("query", "Salad")
                .param("cuisine", "Greek")
                .param("number", 3)
                .log()
                .parameters()
                .expect()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .body("totalResults", is(362))
                .log()
                .body()
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
                .param("apiKey", API_KEY)
                .param("query", "Salad")
                .param("cuisine", "Greek")
                .param("number", 3)
                .param("diet", "Paleo")
                .log()
                .parameters()
                .expect()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .body("totalResults", is(0))
                .log()
                .body()
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