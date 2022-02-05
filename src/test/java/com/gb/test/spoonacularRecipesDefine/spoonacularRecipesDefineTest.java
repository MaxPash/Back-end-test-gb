package com.gb.test.spoonacularRecipesDefine;

import com.gb.test.AbstractTest;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class spoonacularRecipesDefineTest extends AbstractTest {

    @Test
    @DisplayName("Define recipe if cuisine is African test")
    protected void testPostRecipesCuisineAfrican() throws IOException, JSONException {

        String actual = given()
                .queryParam("apiKey", API_KEY)
                .queryParam("title", "African Bean Soup")
                .log()
                .all()
                .expect()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .log()
                .body()
                .when()
                .post("/recipes/cuisine")
                .body()
                .asPrettyString();

        String expected = getResourceAsString("expectedAfrican.json");

        JSONAssert.assertEquals(
                expected,
                actual,
                JSONCompareMode.NON_EXTENSIBLE
        );

    }

    @Test
    @DisplayName("Define recipe if cuisine is American test")
    protected void testPostRecipesCuisineAmerican() throws IOException, JSONException {

        String actual = given()
                .queryParam("apiKey", API_KEY)
                .queryParam("title", "The Best Chili")
                .log()
                .parameters()
                .expect()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .log()
                .body()
                .when()
                .post("/recipes/cuisine")
                .body()
                .asPrettyString();

        String expected = getResourceAsString("expectedAmerican.json");

        JSONAssert.assertEquals(
                expected,
                actual,
                JSONCompareMode.NON_EXTENSIBLE
        );

    }
}
