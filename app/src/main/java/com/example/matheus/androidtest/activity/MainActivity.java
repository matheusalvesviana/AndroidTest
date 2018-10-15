package com.example.matheus.androidtest.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matheus.androidtest.R;
import com.example.matheus.androidtest.adapter.TwitchAdapter;
import com.example.matheus.androidtest.model.Top;
import com.example.matheus.androidtest.model.TopList;
import com.example.matheus.androidtest.network.GetTwitchDataService;
import com.example.matheus.androidtest.network.RetrofitInstance;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TwitchAdapter adapter;
    private RecyclerView recyclerView;
    private GetTwitchDataService service = RetrofitInstance.getRetrofitInstance().create(GetTwitchDataService.class);
    private ArrayList<Top> responseRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SwipeRefreshLayout swipeView = findViewById(R.id.swipe) ;
        swipeView.setColorSchemeResources(R.color.colorPrimary);
        swipeView.setOnRefreshListener(() -> {
            Log.d("Swipe Refresh:","GameList");
            responseRefresh = getSharedPreferencesList();
            generateTwitchList(responseRefresh);
            swipeView.setRefreshing(false);
        });

        /*Create handle for the RetrofitInstance interface*/
        GetTwitchDataService service = RetrofitInstance.getRetrofitInstance().create(GetTwitchDataService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<TopList> call = service.getTwitchDataService("20");

        /*Log the URL called*/


        call.enqueue(new Callback<TopList>() {
            @Override
            public void onResponse(Call<TopList> call, Response<TopList> response) {
                ArrayList<Top> twitchArrayList = response.body().getTwitchArrayList();
                saveToSharedPreferences(twitchArrayList);
                generateTwitchList(twitchArrayList);
            }

            @Override
            public void onFailure(Call<TopList> call, Throwable t) {
                if(getSharedPreferencesList()!=null) {
                    generateTwitchList(getSharedPreferencesList());
                }
                Toast.makeText(MainActivity.this, "Sem conexão com a internet!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of employees using RecyclerView with custom adapter*/
    private void generateTwitchList(ArrayList<Top> gameDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_game_list);

        adapter = new TwitchAdapter(gameDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    public void saveToSharedPreferences(ArrayList<Top> list){
        SharedPreferences mPrefs = getSharedPreferences("object", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        prefsEditor.putString("twitchResponse", json);
        prefsEditor.commit();
    }

    public ArrayList<Top> getSharedPreferencesList(){
        SharedPreferences mPrefs = getSharedPreferences("object", Context.MODE_PRIVATE);
        String jsons = mPrefs.getString("twitchResponse", "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Top>>(){}.getType();
        ArrayList<Top> obj = gson.fromJson(jsons, type);
        return obj;
    }
}
