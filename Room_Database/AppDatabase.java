package com.flourpicker.app.Room_Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.flourpicker.app.Room_Database.dao.AddToCartDao;
import com.flourpicker.app.Room_Database.dao.LocationUserDao;
import com.flourpicker.app.Room_Database.entity.AddToCartCount;
import com.flourpicker.app.Room_Database.entity.AddToCartInfo;
import com.flourpicker.app.Room_Database.entity.LocationUser;

@Database(entities = {LocationUser.class,AddToCartInfo.class, AddToCartCount.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "flourpicker-db";
    public static final String TABLE_NAME_LOCATION = "tbl_location";
    public static final String TABLE_NAME_ADDTOCART = "tbl_addtocart";
    public static final String TABLE_NAME_ADDTOCART_COUNT = "tbl_addtocartcount";

    private static AppDatabase mInstance;

    public static AppDatabase getInstance(Context context){
        if(mInstance == null){
            mInstance = Room.databaseBuilder(context,AppDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return mInstance;
    }


    public abstract LocationUserDao locationUserDao();
    public abstract AddToCartDao addToCartUserDao();

}
