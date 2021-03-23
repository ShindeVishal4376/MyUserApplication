package com.flourpicker.app.Room_Database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.flourpicker.app.Room_Database.entity.AddToCartInfo;
import com.flourpicker.app.Room_Database.entity.LocationUser;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface LocationUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLocation(LocationUser... locationUsers);

    @Query("SELECT * FROM tbl_location")
    Maybe<List<LocationUser>> getAllLocations();

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertAddToCart(AddToCartInfo... addToCartInfos);

    @Query("DELETE FROM tbl_location")
    void deleteByAll();

}
