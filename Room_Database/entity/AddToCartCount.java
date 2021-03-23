package com.flourpicker.app.Room_Database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.flourpicker.app.Room_Database.AppDatabase;

@Entity(tableName = AppDatabase.TABLE_NAME_ADDTOCART_COUNT)
public class AddToCartCount {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "cartCount")
    private Integer cartCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }
}
