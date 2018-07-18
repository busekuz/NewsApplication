package com.example.android.newsapp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GuardianAPIService {


    @GET("search?")
    Call<ArrayList<New>> listNews(@Query("q") String category, @Query("show-fields") String fields, @Query("api-key") String key);

}

