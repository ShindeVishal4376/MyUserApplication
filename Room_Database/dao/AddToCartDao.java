package com.flourpicker.app.Room_Database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.flourpicker.app.Room_Database.entity.AddToCartCount;
import com.flourpicker.app.Room_Database.entity.AddToCartInfo;

import java.util.List;

import io.reactivex.Maybe;


@Dao
public interface AddToCartDao {

    @Query("SELECT * FROM tbl_addtocart")
    Maybe<List<AddToCartInfo>> getAllAddToCart();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAddToCart(AddToCartInfo... addToCartInfos);

    @Query("DELETE FROM tbl_addtocart WHERE productId = :productId")
     void deleteById(long productId);

    @Query("DELETE FROM tbl_addtocart")
    void deleteByAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAddToCartCount(AddToCartCount... addToCartCountInfos);

    @Query("SELECT * FROM tbl_addtocartcount")
    Maybe<AddToCartCount> getAllAddToCartCount();

}
