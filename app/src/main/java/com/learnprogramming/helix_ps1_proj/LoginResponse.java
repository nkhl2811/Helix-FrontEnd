package com.learnprogramming.helix_ps1_proj;

public class LoginResponse {
    private String message;
    private boolean success;
    private String token;
    private User user;

    public LoginResponse(String message, boolean success, String token, User user) {
        this.message = message;
        this.success = success;
        this.token = token;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
