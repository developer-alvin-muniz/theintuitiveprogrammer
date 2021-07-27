package com.alvinmuniz.blog.model.Login;

public class LoginResponse {

    private String jwt;

    public LoginResponse() {}

    public LoginResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
