package com.rv_auto_seller.dto.response;

public class LoginResponse {
    private String userToken;
    private String password;

    public LoginResponse(String userToken, String password) {
        this.userToken = userToken;
        this.password = password;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
