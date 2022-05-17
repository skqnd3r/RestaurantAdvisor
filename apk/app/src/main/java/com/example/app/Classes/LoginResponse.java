package com.example.app.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("errors")
    @Expose
    private String errors;

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public String getErrors() {
        return errors;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
