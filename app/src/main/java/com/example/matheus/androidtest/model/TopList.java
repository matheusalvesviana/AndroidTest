package com.example.matheus.androidtest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TopList {

    @SerializedName("top")
    private ArrayList<Top> topList;

    public ArrayList<Top> getTwitchArrayList() {
        return topList;
    }

    public void setTwitchArrayList(ArrayList<Top> topArrayList) {
        this.topList = topArrayList;
    }
}
