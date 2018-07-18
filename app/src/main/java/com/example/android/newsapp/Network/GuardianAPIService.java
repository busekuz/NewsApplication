package com.example.android.newsapp.Network;

import com.example.android.newsapp.Class.New;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GuardianAPIService {

    //  https://content.guardianapis.com/search?q=technology&show-fields=all&api-key=01216ad2-f602-42c3-a90b-0a1f5e980937

    @GET("search")
    Call<New> listNews(@Query("q") String category, @Query("show-fields") String fields, @Query("api-key") String key);

}

