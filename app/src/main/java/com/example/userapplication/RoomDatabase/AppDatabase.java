package com.example.userapplication.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.userapplication.RoomDatabase.dao.AddUserListDao;
import com.example.userapplication.RoomDatabase.entity.AddUserList;

@Database(entities = {AddUserList.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "Userlist-db";
    public static final String TABLE_NAME_USER_LIST = "tbl_userList";

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

    public abstract AddUserListDao addUserListDao();

}
