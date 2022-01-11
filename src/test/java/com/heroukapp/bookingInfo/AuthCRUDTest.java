package com.heroukapp.bookingInfo;

import com.heroukapp.studentinfo.AuthSteps;
import com.heroukapp.testBase.TestBaseAuth;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SerenityRunner.class)
public class AuthCRUDTest extends TestBaseAuth {

    static String username = "admin";
    static String password = "password123";


    @Steps
    AuthSteps authSteps;

    @Title("This will create a new token")
    @Test
    public void test001() {
        ValidatableResponse response = authSteps.createToken(username, password);
        response.log().all().statusCode(200);

    }
}
