package com.example.app.Classes;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Menu implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("restaurant_id")
    @Expose
    private String restaurant_id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    private String image = null;

    public String getData(String data) {
        String result = "";
        switch (data) {
            case "id":
                result = this.id;
                break;
            case "restaurant_id":
                result = this.restaurant_id;
                break;
            case "name":
                result = this.name;
                break;
            case "description":
                result = this.description;
                break;
            case "price":
                result = this.price;
                break;
            case "image":
                result = this.image;
                break;
        }
        return result;
    }
//
    public void setImage(String image) {
        switch (image) {
            case "http://10.0.2.2:8000/api/restaurant/image/resto_tacos.jpg":
                this.image = "http://10.0.2.2:8000/api/restaurant/image/tacos.jpg";
                break;
            case "http://10.0.2.2:8000/api/restaurant/image/resto_sushis.jpg":
                this.image = "http://10.0.2.2:8000/api/restaurant/image/sushis.jpg";
                break;
            case "http://10.0.2.2:8000/api/restaurant/image/resto_burger.jpg":
                this.image = "http://10.0.2.2:8000/api/restaurant/image/burger.jpg";
                break;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
