package com.example.matheus.androidtest.adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matheus.androidtest.R;
import com.example.matheus.androidtest.activity.GameActivity;
import com.example.matheus.androidtest.activity.util.Util;
import com.example.matheus.androidtest.database.IFavoritosDatabase;
import com.example.matheus.androidtest.database.IFavoritosEntity;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {


    private ArrayList<IFavoritosEntity.Game> dataList;

    public FavoritesAdapter(ArrayList<IFavoritosEntity.Game> dataList) {
        this.dataList = dataList;
    }

    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_game_card, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {

        int widthPixels = holder.imgGame.getContext().getResources().getDisplayMetrics().widthPixels;
        Picasso.get().load(dataList.get(position).getBox().getLarge()).resize( Util.convertDpToPx(350,holder.imgGame.getContext().getResources().getDisplayMetrics() ), Util.convertDpToPx(350,holder.imgGame.getContext().getResources().getDisplayMetrics())).centerCrop().into(holder.imgGame);
        holder.txtGameName.setText(dataList.get(position).getName());
        holder.txtGamePopularity.setText(String.valueOf(dataList.get(position).getPopularity()));
        holder.itemView.setOnClickListener((view)->{
            Intent intent = new Intent(view.getContext().getApplicationContext(), GameActivity.class);
            intent.putExtra("game_image",dataList.get(position).getBox().getLarge());
            intent.putExtra("game_name",dataList.get(position).getName());
            intent.putExtra("game_pop",String.valueOf(dataList.get(position).getPopularity()));
            intent.putExtra("game_id",String.valueOf(dataList.get(position).getId()));
            view.getContext().getApplicationContext().startActivity(intent);

        });
        holder.imgFavorites.setOnClickListener((view)->{
            IFavoritosDatabase.insertIdFavorites(IFavoritosDatabase.AppDatabase.getAppDatabase(view.getContext()),dataList.get(position).getId());
        });

    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class FavoritesViewHolder extends RecyclerView.ViewHolder {

        TextView txtGameName, txtGamePopularity, txtEmpPhone;
        ImageView imgGame;
        CheckBox imgFavorites;

        FavoritesViewHolder(View itemView) {
            super(itemView);
            imgGame = (ImageView) itemView.findViewById(R.id.img_game);
            txtGameName = (TextView) itemView.findViewById(R.id.txt_game_name);
            txtGamePopularity = (TextView) itemView.findViewById(R.id.txt_game_popularity);
            imgFavorites = (CheckBox) itemView.findViewById(R.id.img_favorite);
        }
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
