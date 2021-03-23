package com.example.userapplication.RoomDatabase;

import android.content.Context;

import androidx.room.Room;

public class DBHelper {

    private static DBHelper instance;
    private static AppDatabase db;
    private Context context;

    public static  DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    public DBHelper(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DB_NAME).build();
    }
}
