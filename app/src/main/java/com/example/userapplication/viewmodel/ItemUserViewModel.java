package com.example.userapplication.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.example.userapplication.model.JsonResponseDatum;
import com.example.userapplication.model.UserData;

public class ItemUserViewModel extends BaseObservable {
    private Context mContext;
    private UserData mUserData;

    public ItemUserViewModel(Context context, UserData userData) {
        this.mContext = context;
        this.mUserData = userData;
    }

    public void setUserData(UserData userData) {
        mUserData = userData;
        notifyChange();
    }


    public String getName() {
        return mUserData.getFirstName();
    }

    public String getLastName() {
        return mUserData.getLastName();
    }

    public String getEmail() {
        return mUserData.getEmail();
    }
}
