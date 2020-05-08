package com.store.payload;

public class LoginResponsePayload {
    private String accessToken;
    private String tokenType="BEARER";

    public LoginResponsePayload() {
    }

    public LoginResponsePayload(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
