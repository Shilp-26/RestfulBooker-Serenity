package com.heroukapp.model;

import com.heroukapp.utils.TestUtils;

import java.util.List;

public class AuthPojo {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public static AuthPojo getAuthPojo(String username, String password) {
        AuthPojo authPojo =new AuthPojo();
        authPojo.setUsername(username);
        authPojo.setPassword(password);
        return authPojo;
    }
}
