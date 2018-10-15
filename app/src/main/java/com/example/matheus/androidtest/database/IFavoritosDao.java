package com.example.matheus.androidtest.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

import com.example.matheus.androidtest.network.GetTwitchDataService;

import java.util.List;

@Dao
public interface IFavoritosDao {

        @android.arch.persistence.room.Query("SELECT * FROM game")
        List<IFavoritosEntity.Game> getAll();

        @android.arch.persistence.room.Query("SELECT * FROM game where _id LIKE  :id ")
        int findByName(int id);
//
//        @android.arch.persistence.room.Query("SELECT COUNT(*) from user")
//        int countUsers();

        @Insert
        void insertFavorite(IFavoritosEntity.Game id);

        @Delete
        void delete(IFavoritosEntity.Game id);
}
