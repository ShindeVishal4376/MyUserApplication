package com.flourpicker.app.Room_Database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.flourpicker.app.Room_Database.AppDatabase;

@Entity(tableName = AppDatabase.TABLE_NAME_LOCATION)
public class LocationUser {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "areaId")
    private Integer areaId;

    @ColumnInfo(name = "cityEng")
    private String cityEng;

    @ColumnInfo(name = "cityHin")
    private String cityHin;

    @ColumnInfo(name = "cityMar")
    private String cityMar;

    @ColumnInfo(name = "areaNameENg")
    private String areaNameENg;

    @ColumnInfo(name = "areaNameHin")
    private String areaNameHin;

    @ColumnInfo(name = "areaNameMar")
    private String areaNameMar;

    @ColumnInfo(name = "pincode")
    private String pincode;

    @ColumnInfo(name = "isServiceAvailable")
    private boolean isServiceAvailable;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }


    public LocationUser() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityEng() {
        return cityEng;
    }

    public void setCityEng(String cityEng) {
        this.cityEng = cityEng;
    }

    public String getCityHin() {
        return cityHin;
    }

    public void setCityHin(String cityHin) {
        this.cityHin = cityHin;
    }

    public String getCityMar() {
        return cityMar;
    }

    public void setCityMar(String cityMar) {
        this.cityMar = cityMar;
    }

    public String getAreaNameENg() {
        return areaNameENg;
    }

    public void setAreaNameENg(String areaNameENg) {
        this.areaNameENg = areaNameENg;
    }

    public String getAreaNameHin() {
        return areaNameHin;
    }

    public void setAreaNameHin(String areaNameHin) {
        this.areaNameHin = areaNameHin;
    }

    public String getAreaNameMar() {
        return areaNameMar;
    }

    public void setAreaNameMar(String areaNameMar) {
        this.areaNameMar = areaNameMar;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public boolean isServiceAvailable() {
        return isServiceAvailable;
    }

    public void setServiceAvailable(boolean serviceAvailable) {
        isServiceAvailable = serviceAvailable;
    }
}
