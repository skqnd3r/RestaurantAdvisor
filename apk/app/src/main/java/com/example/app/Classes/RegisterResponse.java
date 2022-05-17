package com.example.app.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("email")
    @Expose
    private String email;

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }
}
