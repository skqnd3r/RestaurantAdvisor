package com.example.app.Classes;

import androidx.lifecycle.ViewModel;

import java.io.Serializable;

public class Session extends ViewModel implements Serializable  {
    private String token = null;
    private User user = new User();

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
