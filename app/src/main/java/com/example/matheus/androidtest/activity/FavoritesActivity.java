package com.example.matheus.androidtest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.matheus.androidtest.R;
import com.example.matheus.androidtest.adapter.FavoritesAdapter;
import com.example.matheus.androidtest.database.IFavoritosDatabase;
import com.example.matheus.androidtest.database.IFavoritosEntity;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity{

    private RecyclerView recyclerView ;
    private FavoritesAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<IFavoritosEntity.Game> call = IFavoritosDatabase.getFavoriteList(IFavoritosDatabase.AppDatabase.getAppDatabase(this.getApplicationContext()));

        for(int i=0;i<call.size();i++){
            generateFavoritesList((ArrayList<IFavoritosEntity.Game>) call);
        }
    }


    private void generateFavoritesList(ArrayList<IFavoritosEntity.Game> gameDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_favorite_list);

        adapter = new FavoritesAdapter(gameDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FavoritesActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}
