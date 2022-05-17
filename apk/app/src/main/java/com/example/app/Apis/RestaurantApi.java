package com.example.app.Apis;

import com.example.app.Classes.Restaurant;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestaurantApi {

    @GET("restaurants")
    Call<List<Restaurant>> getRestaurants();

    @POST("restaurant")
    Call<Restaurant> postRestaurant(@Header("Authorization:") String token, @Body Restaurant restaurant);

    @PUT("restaurant/{id}")
    Call<Restaurant> putRestaurant(@Body Restaurant restaurant, @Path("id") int id);

    @DELETE("restaurant/{id}")
    Call<ResponseBody> deleteRestaurant(@Header("Authorization:") String token, @Path("id") String id);
}