package com.example.matheus.androidtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matheus.androidtest.R;
import com.example.matheus.androidtest.activity.util.Util;
import com.example.matheus.androidtest.adapter.TwitchAdapter;
import com.squareup.picasso.Picasso;

public class GameActivity extends AppCompatActivity{

    private static final String TAG = "GameActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent show = getIntent();
        

        ImageView imgGame = (ImageView) findViewById(R.id.img_game_info);
        TextView txtGameName = (TextView) findViewById(R.id.txt_game_name_info);
        TextView txtGamePopularity = (TextView) findViewById(R.id.txt_game_popularity_info);


        Picasso.get().load(show.getStringExtra("game_image")).resize(  Util.convertDpToPx(350,imgGame.getContext().getResources().getDisplayMetrics() ), Util.convertDpToPx(350,imgGame.getContext().getResources().getDisplayMetrics())).into(imgGame);
        txtGameName.setText(show.getStringExtra("game_name"));
        txtGamePopularity.setText(show.getStringExtra("game_pop"));


        //TODO: Adicionar ação de clique (Criar um Fragment?)
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(show.getStringExtra("game_name"));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setIcon(R.drawable.ic_launcher_background);
        }

    }

   
            
            
        
    


}
