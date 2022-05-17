package com.example.app.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Restaurant implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("grade")
    @Expose
    private String grade;
    @SerializedName("localization")
    @Expose
    private String localization;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("phone_number")
    @Expose
    private String phone_number;
    @SerializedName("hours")
    @Expose
    private String hours;
    @SerializedName("id")
    @Expose
    private String id;

    private String imageURL;

    public String getData(String data) {
        String result = "";
        switch (data) {
            case "name":
                result = this.name;
                break;
            case "description":
                result = this.description;
                break;
            case "grade":
                result = this.grade;
                break;
            case "localization":
                result = this.localization;
                break;
            case "website":
                result = this.website;
                break;
            case "phone_number":
                result = this.phone_number;
                break;
            case "hours":
                result = this.hours;
                break;
            case "id":
                result = this.id;
                break;
            case "image":
                result = this.imageURL;
                break;
        }
        return result;
    }

    public void updateData(String data, String newData) {
        switch (data) {
            case "name":
                this.name = newData;
                break;
            case "description":
                this.description = newData;
                break;
            case "grade":
                this.grade = newData;
                break;
            case "localization":
                this.localization = newData;
                break;
            case "website":
                this.website = newData;
                break;
            case "phone_number":
                this.phone_number = newData;
                break;
            case "hours":
                this.hours = newData;
                break;
            case "id":
                this.id = newData;
                break;
            case "image":
                this.imageURL = newData;
                break;
        }
    }
}
