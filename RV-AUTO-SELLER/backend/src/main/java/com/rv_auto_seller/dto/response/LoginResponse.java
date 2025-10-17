package com.rv_auto_seller.dto.response;

public class LoginResponse {
    private String userToken;
    private String password;

    public LoginResponse(String username, String password) {
        this.userToken = username;
        this.password = password;
    }

    public String getUsername() {
        return userToken;
    }

    public void setUsername(String username) {
        this.userToken = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
