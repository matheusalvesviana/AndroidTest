package com.example.matheus.androidtest.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

public interface IFavoritosEntity {


    @Entity(tableName = "game")
    public class Game {

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getGame_id() {
            return game_id;
        }

        public void setGame_id(int game_id) {
            this.game_id = game_id;
        }

        @PrimaryKey(autoGenerate = true)
        private int uid;

        @ColumnInfo(name = "_id")
        private int game_id;
    }
}
