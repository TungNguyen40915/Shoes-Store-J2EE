package com.store.DTO;

public class AdminLoginResponse {
    private String token;

    public AdminLoginResponse() {
    }

    public AdminLoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
