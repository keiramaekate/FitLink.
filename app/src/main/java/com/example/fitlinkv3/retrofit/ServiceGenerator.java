package com.example.fitlinkv3.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static final String BASE_URL = "https://www.strava.com/api/v3/";
    private static Retrofit.Builder localPlacesBuilder = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static StravaAPI stravaAPI;

    public static <S> S createLocalPlacesService(Class<S> serviceClass) {

        httpClient.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);
        httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();

        Retrofit retrofit = localPlacesBuilder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    public static StravaAPI getEndPointInterface() {
        if (stravaAPI == null) {
            stravaAPI = ServiceGenerator.createLocalPlacesService(StravaAPI.class);
        }
        return stravaAPI;
    }
}
