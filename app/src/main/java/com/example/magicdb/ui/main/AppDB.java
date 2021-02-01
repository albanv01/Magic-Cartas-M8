package com.example.magicdb.ui.main;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CartasObject.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    private static AppDB INSTANCE;

    public static AppDB getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDB.class, "db"
            ).build();
        }
        return INSTANCE;
    }

    public abstract CartasDAO getCartasDao();
}
