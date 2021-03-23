package com.flourpicker.app.Room_Database;

import android.content.Context;

import androidx.room.Room;

import com.flourpicker.app.Room_Database.entity.LocationUser;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DBHelper {

    private static DBHelper instance;
//    public static final String DB_NAME = "flourpicker-db";
    private static AppDatabase db;
    private Context context;
    static List<LocationUser> mAllLocations = new ArrayList<>();

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


  /*  public static List<LocationUser> getLocations() {

        db.locationUserDao().getAllLocations().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<LocationUser>>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull List<LocationUser> likePost) throws Exception {
//                likePostView.getLikePostView(likePost);
                LocationUser user = new LocationUser();
//                mAllLocations.add(likePost.get(0));
                user = likePost;
            }
        });

    }*/
}
