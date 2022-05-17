package com.example.app.Apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Api {

    private static final Retrofit retrofit = setRetrofit();

    protected static final RestaurantApi restaurantApi = retrofit.create(RestaurantApi.class);
    protected static final UserApi userApi = retrofit.create(UserApi.class);
    protected static final MenuApi menuApi = retrofit.create(MenuApi.class);
    protected static final GradeApi gradeApi = retrofit.create(GradeApi.class);

    protected static Retrofit setRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit r = new Retrofit.Builder().baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return r;
    }

    public static RestaurantApi RestaurantApi() { return restaurantApi; }

    public static UserApi UserApi() { return userApi; }

    public static MenuApi MenuApi() { return menuApi; }

    public static GradeApi GradeApi() { return gradeApi; }
}
