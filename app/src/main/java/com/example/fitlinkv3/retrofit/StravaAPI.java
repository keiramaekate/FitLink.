package com.example.fitlinkv3.retrofit;

import com.example.fitlinkv3.StravaAuth;
import com.samsandberg.stravaauthenticator.StravaAuthenticateActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StravaAPI
{

    //get athlete information
    @GET("athlete")
    Call<Athlete> getAthlete(@Header("Authorization") String value);

    @GET("athletes/{id}/stats")
    Call<ActivityStats> getStats(@Header("Authorization") String value, @Path("id") int stravaId);

}
