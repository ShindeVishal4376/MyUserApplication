package com.example.userapplication.RoomDatabase.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.userapplication.RoomDatabase.entity.AddUserList;

import java.util.List;

@Dao
public interface AddUserListDao {

    @Query("SELECT * FROM tbl_userList LIMIT 4 OFFSET :offset")
    List<AddUserList> getAllAddUserList(int offset);

    @Query("SELECT * FROM tbl_userList ")
    List<AddUserList> getAllAddUserListCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAddUserList(AddUserList... AddUserListInfos);

    @Query("DELETE FROM tbl_userList")
    void deleteByAll();
}
