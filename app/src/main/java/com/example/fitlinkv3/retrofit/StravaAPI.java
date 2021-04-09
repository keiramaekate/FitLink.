package com.example.fitlinkv3.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StravaAPI
{

    //@POST("user/authenticate")
    //Call<String> login(@Body LoginData loginData);

    //get athlete information
    @GET("athlete")
    Call<Athlete> getAthlete(@Header("Authorization") String value);


}
