package com.example.matheus.androidtest.network;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.matheus.androidtest.model.TopList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetTwitchDataService {
    @GET("top?client_id=q4mxz31bu26lmab8is22aymzoxzc7v")
    Call<TopList> getTwitchDataService(@Query("limit") String limit);
}
