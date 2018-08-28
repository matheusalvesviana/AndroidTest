package com.example.matheus.androidtest.adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matheus.androidtest.R;
import com.example.matheus.androidtest.activity.GameActivity;
import com.example.matheus.androidtest.activity.util.Util;
import com.example.matheus.androidtest.model.Top;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class TwitchAdapter extends RecyclerView.Adapter<TwitchAdapter.TwitchViewHolder> {


    private ArrayList<Top> dataList;

    public TwitchAdapter(ArrayList<Top> dataList) {
        this.dataList = dataList;
    }

    @Override
    public TwitchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_game_card, parent, false);
        return new TwitchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TwitchViewHolder holder, int position) {

        int widthPixels = holder.imgGame.getContext().getResources().getDisplayMetrics().widthPixels;
        Picasso.get().load(dataList.get(position).getGame().getBox().getLarge()).resize( Util.convertDpToPx(350,holder.imgGame.getContext().getResources().getDisplayMetrics() ), Util.convertDpToPx(350,holder.imgGame.getContext().getResources().getDisplayMetrics())).centerCrop().into(holder.imgGame);
        holder.txtGameName.setText(dataList.get(position).getGame().getName());
        holder.txtGamePopularity.setText(String.valueOf(dataList.get(position).getGame().getPopularity()));
        holder.itemView.setOnClickListener((view)->{
            Intent intent = new Intent(view.getContext().getApplicationContext(), GameActivity.class);
            intent.putExtra("game_image",dataList.get(position).getGame().getBox().getLarge());
            intent.putExtra("game_name",dataList.get(position).getGame().getName());
            intent.putExtra("game_pop",String.valueOf(dataList.get(position).getGame().getPopularity()));
            view.getContext().getApplicationContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class TwitchViewHolder extends RecyclerView.ViewHolder {

        TextView txtGameName, txtGamePopularity, txtEmpPhone;
        ImageView imgGame;

        TwitchViewHolder(View itemView) {
            super(itemView);
            imgGame = (ImageView) itemView.findViewById(R.id.img_game);
            txtGameName = (TextView) itemView.findViewById(R.id.txt_game_name);
            txtGamePopularity = (TextView) itemView.findViewById(R.id.txt_game_popularity);
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
