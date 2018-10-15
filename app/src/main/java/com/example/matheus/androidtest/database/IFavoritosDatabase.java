package com.example.matheus.androidtest.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.List;

public interface IFavoritosDatabase {

    @Database(entities = {IFavoritosEntity.Game.class}, version = 1,exportSchema = false)
    public abstract class AppDatabase extends RoomDatabase {

        private static AppDatabase INSTANCE;

        public abstract  IFavoritosDao userDao();

        public static AppDatabase getAppDatabase(Context context) {
            if (INSTANCE == null) {
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                                // allow queries on the main thread.
                                // Don't do this on a real app! See PersistenceBasicSample for an example.
                                .allowMainThreadQueries()
                                .build();
            }
            return INSTANCE;
        }

        public static void destroyInstance() {
            INSTANCE = null;
        }
    }

    static IFavoritosEntity.Game addUser(final IFavoritosDatabase.AppDatabase db, IFavoritosEntity.Game user) {
        db.userDao().insertFavorite(user);
        return user;
    }

    IFavoritosDao userDao();

    static int getFavorite(final IFavoritosDatabase.AppDatabase db, int id) {
        return db.userDao().findByName(id);
    }

    static List<IFavoritosEntity.Game> getFavoriteList(final AppDatabase db) {
        return db.userDao().getAll();
    }

    static void insertIdFavorites(IFavoritosDatabase.AppDatabase db, int id) {
        IFavoritosEntity.Game user = new IFavoritosEntity.Game();
        user.setGame_id(id);
        addUser(db, user);
    }


}
