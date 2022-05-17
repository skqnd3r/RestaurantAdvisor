package com.example.app.Apis;

import com.example.app.Classes.Grade;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GradeApi {

    @GET("restaurant/{id}/grades")
    Call<List<Grade>> getComments(@Path("id") String id);

    @POST("restaurant/{id}/grade")
    Call<Grade> postGrade(@Header("Authorization:") String token, @Path("id") String id, @Body Grade grade);

    @PUT("grade/{id}")
    Call<Grade> putGrade(@Header("Authorization:") String token, @Path("id") String id);

    @DELETE("grade/{id}")
    Call<Grade> deleteGrade(@Header("Authorization:") String token, @Path("id") String id);
}