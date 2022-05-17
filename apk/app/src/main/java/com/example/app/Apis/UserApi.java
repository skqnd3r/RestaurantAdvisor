package com.example.app.Apis;

import com.example.app.Classes.Restaurant;
import com.example.app.Classes.Grade;
import com.example.app.Classes.User;
import com.example.app.Classes.LoginRequest;
import com.example.app.Classes.LoginResponse;
import com.example.app.Classes.RegisterRequest;
import com.example.app.Classes.RegisterResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserApi {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("user/restaurants")
    Call<List<Restaurant>> getUserRestaurants(@Header("Authorization:") String token);

    @GET("user/grades")
    Call<List<Grade>> getUserGrades(@Header("Authorization:") String token);

    @POST("register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @POST("auth")
    Call<LoginResponse> authenticateUser(@Body LoginRequest loginRequest);

    @POST("disconnect")
    Call<String> disconnectUser(@Header("Authorization:") String token);

    @DELETE("user")
    Call<ResponseBody> deleteUser(@Header("Authorization:") String token);

    @PUT("user")
    Call<User> modifyUser(@Header("Authorization:") String token, @Body User user);

}
