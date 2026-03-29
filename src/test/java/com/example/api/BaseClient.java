package com.example.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseClient {
    protected RequestSpecification request() {
        return RestAssured.given()
                .baseUri(ApiConfig.BASE_URL)
                .contentType("application/json")
                .log().all(true)
                .filter(new AllureRestAssured());
    }
}