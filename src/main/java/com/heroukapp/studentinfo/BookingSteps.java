package com.heroukapp.studentinfo;

import com.heroukapp.constants.EndPoints;
import com.heroukapp.model.BookingDates;
import com.heroukapp.model.BookingPojo;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookingSteps {


    @Step("Creating Booking with firstname : {0}, lastname: {1}, totalprice: {2}, depositpaid: {3},bookingdates: {4},additionalneeds: {5}")
    public ValidatableResponse createBooking(String firstname, String lastname, int totalprice, boolean depositpaid, BookingDates bookingdates, String additionalneeds) {
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(bookingPojo)
                .when()
                .post()
                .then();
    }

    @Step("Getting the booking information with booking firstname: {0}")
    public ArrayList<HashMap<String, Object>> getBookingInfoByFirstname(String firstname) {
        String p1 = "findAll{it.firstName='";
        String p2 = "'}";
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_BOOKINGS_BY_NAME + firstname)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + firstname + p2);
    }

    @Step("Updating booking information with bookingID: {0}, firstname: {1}, lastname: {2}, totalprice: {3}, depositpaid: {4} and bookingdates: {5},additionalneeds: {6}")
    public ValidatableResponse updateBooking(int bookingID, String firstname, String lastname, int totalprice, boolean depositpaid, BookingDates bookingdates, String additionalneeds) {
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);

        //To get Token after Authentication
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme(); // create object of PreemptiveBasicAuthScheme class
        authScheme.setUserName("admin"); // assign the username to object
        authScheme.setPassword("password123"); // assign the password to object
        RestAssured.authentication = authScheme; // assign the object to RestAssured.authentication class

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("bookingID", bookingID)
                .body(bookingPojo)
                .when()
                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then();
    }

    @Step("Deleting booking information with bookingID: {0}")
    public ValidatableResponse deleteBooking(int bookingID) {

        //To get Token after Authentication
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme(); // create object of PreemptiveBasicAuthScheme class
        authScheme.setUserName("admin"); // assign the username to object
        authScheme.setPassword("password123"); // assign the password to object
        RestAssured.authentication = authScheme; // assign the object to RestAssured.authentication class

        return SerenityRest
                .given()
                .pathParam("bookingID", bookingID)
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then();
    }

    @Step("Getting booking information with bookingId: {0}")
    public ValidatableResponse getBookingById(int bookingID) {
        return SerenityRest
                .given()
                .pathParam("bookingID", bookingID)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then();
    }
}
