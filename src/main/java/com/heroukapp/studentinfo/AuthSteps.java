package com.heroukapp.studentinfo;

import com.heroukapp.model.AuthPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class AuthSteps {

    @Step("Creating auth token with username : {0}, password: {1}")
    public ValidatableResponse createToken(String username, String password) {
        AuthPojo authPojo = AuthPojo.getAuthPojo(username, password);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(authPojo)
                .when()
                .post()
                .then();

    }
}
