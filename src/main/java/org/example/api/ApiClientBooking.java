package org.example.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.api.models.Booking;

import static io.restassured.RestAssured.given;

public class ApiClientBooking {

    private final RequestSpecification requestSpec;

    public ApiClientBooking(String baseUrl) {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .setAccept("application/json")
                .log(LogDetail.ALL)
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public Response createBooking(Booking booking) {
        return given(requestSpec)
                .body(booking)
                .when()
                .post("/booking");
    }

    public Response getBooking(int id) {
        return given(requestSpec)
                .when()
                .get("/booking/{id}", id);
    }
}