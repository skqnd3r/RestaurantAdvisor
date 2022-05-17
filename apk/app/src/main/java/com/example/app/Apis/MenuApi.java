package com.example.app.Apis;

import com.example.app.Classes.Menu;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.Call;

public interface MenuApi {
    @GET("restaurant/{id}/menus")
    Call<List<Menu>> getMenus(@Path("id") String id);

    @POST("restaurant/{id}/menu")
    Call<Menu> postMenu(@Header("Authorization:") String token,@Path("id") String id,@Body Menu menu);
}