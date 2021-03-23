package com.example.userapplication.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.example.userapplication.model.JsonResponseDatum;

public class ItemUserViewModel extends BaseObservable {
    private Context mContext;
    private JsonResponseDatum mJsonResponseDatum;

    public ItemUserViewModel(Context context, JsonResponseDatum jsonResponseDatum) {
        this.mContext = context;
        this.mJsonResponseDatum = jsonResponseDatum;
    }

    public void setUserData(JsonResponseDatum jsonResponseDatum) {
        mJsonResponseDatum = jsonResponseDatum;
        notifyChange();
    }


    public String getName() {
        return mJsonResponseDatum.getName();
    }

    public String getUserName() {
        return mJsonResponseDatum.getUsername();
    }

    public String getPhone() {
        return mJsonResponseDatum.getPhone();
    }

    public String getEmail() {
        return mJsonResponseDatum.getEmail();
    }

    public String getAddress() {
        if (mJsonResponseDatum.getAddress() != null) {
            return mJsonResponseDatum.getAddress().getStreet() + "," + mJsonResponseDatum.getAddress().getSuite() + "," + mJsonResponseDatum.getAddress().getCity() + "," + mJsonResponseDatum.getAddress().getZipcode();
        } else {
            return "";
        }
    }

    public String getWebsite() {
        return mJsonResponseDatum.getWebsite();
    }

    public String getCompany() {
        if (mJsonResponseDatum.getCompany() != null) {
            return mJsonResponseDatum.getCompany().getName();
        } else {
            return "";
        }
    }
}
