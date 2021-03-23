package com.example.userapplication.Interface;

import com.example.userapplication.RoomDatabase.entity.AddUserList;

import java.util.List;

public interface UserListInfo {

    void getDisplayUserList(List<AddUserList> addUserList);
}
