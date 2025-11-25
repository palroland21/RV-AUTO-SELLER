package com.rv_auto_seller.dto.response;

public class LoginResponse {
    private String userToken;

    public LoginResponse(String userToken) {
        this.userToken = userToken;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

}
