package com.example.app.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Grade implements Serializable {
    //import all data
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("restaurant_id")
    @Expose
    private String restaurant_id;

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("comment")
    @Expose
    private String comment;

    public String getData(String data) {
        String result = "";
        switch (data) {
            case "id":
                result = this.id;
                break;
            case "user_id":
                result = this.user_id;
                break;
            case "restaurant_id":
                result = this.restaurant_id;
                break;
            case "value":
                result = this.value;
                break;
            case "comment":
                result = this.comment;
                break;
        }
        return result;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
