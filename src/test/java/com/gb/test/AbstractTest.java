package com.gb.test;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;

public class AbstractTest {

    protected static String API_KEY = "603cef618e52469c8d3591641acd27c6";

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://api.spoonacular.com";
    }

    protected String getResourceAsString(String resource) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(resource);
        assert inputStream != null;
        byte[] bytes = inputStream.readAllBytes();
        return new String(bytes);
    }

}