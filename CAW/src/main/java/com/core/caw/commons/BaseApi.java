package com.core.caw.commons;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseApi {

    public Response get(String bearerToken, String url) {
        return given().headers(
                        "Authorization", "Bearer " + bearerToken,
                        "Content-Type", ContentType.JSON,
                        "Accept", ContentType.JSON).when()
                .get(url)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();
    }

    public Response post(String bearerToken, String requestBody, String url) {
        return given()
                .headers(
                        "Authorization", "Bearer " + bearerToken,
                        "Content-Type", ContentType.JSON,
                        "Accept", ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post(url)
                .then()
                .extract().response();
    }

    public Response put(String bearerToken, String requestBody, String url) {
        return given()
                .headers(
                        "Authorization", "Bearer " + bearerToken,
                        "Content-Type", ContentType.JSON,
                        "Accept", ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .put(url)
                .then()
                .extract().response();
    }

    public Response delete(String bearerToken, String requestBody, String url) {
        return given()
                .headers(
                        "Authorization", "Bearer " + bearerToken,
                        "Content-Type", ContentType.JSON,
                        "Accept", ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .delete(url)
                .then()
                .extract().response();
    }

    public Response retrieve(String bearerToken, String url) {
        return given().headers(
                        "Authorization", "Bearer " + bearerToken,
                        "Content-Type", ContentType.JSON,
                        "Accept", ContentType.JSON).when()
                .get(url)
                .then()
                .extract()
                .response();
    }
}
