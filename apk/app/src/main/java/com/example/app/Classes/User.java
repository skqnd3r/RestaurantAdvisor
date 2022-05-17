package com.example.app.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("id")
    @Expose
    private String id;

    public String getData(String data) {
        String result = "";
        switch (data) {
            case "username":
                result = this.username;
                break;
            case "name":
                result = this.name;
                break;
            case "firstname":
                result = this.firstname;
                break;
            case "email":
                result = this.email;
                break;
            case "age":
                result = this.age;
                break;
            case "id":
                result = this.id;
                break;
        }
        return result;
    }

    public void setData(String data, String newData) {
        switch (data) {
            case "login":
                this.login = newData;
                break;
            case "name":
                this.name = newData;
                break;
            case "firstname":
                this.firstname = newData;
                break;
            case "email":
                this.email = newData;
                break;
            case "age":
                this.age = newData;
                break;
        }
    }
}
