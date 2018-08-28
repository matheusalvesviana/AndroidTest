package com.example.matheus.androidtest.network;

import com.example.matheus.androidtest.model.TopList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetTwitchDataService {
    @GET("top?client_id=q4mxz31bu26lmab8is22aymzoxzc7v")
    Call<TopList> getTwitchDataService(@Query("limit") String limit);
}
