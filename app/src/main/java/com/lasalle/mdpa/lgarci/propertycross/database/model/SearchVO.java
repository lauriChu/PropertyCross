package com.lasalle.mdpa.lgarci.propertycross.database.model;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by FurruPi on 15/1/17.
 */

public class SearchVO extends DataBaseModel implements Serializable{
    public static final String TABLE = "Search";

    public static String COL_ID = "Id";
    public static String COL_TITLE = "Search";
    public static String COL_LAT = "Lat";
    public static String COL_LNG = "Lng";

    public static final String[] COLUMNS = { COL_ID, COL_TITLE, COL_LAT, COL_LNG };

    private Integer id;
    private String search;
    private Float lat;
    private Float lng;

    public SearchVO() {}

    public SearchVO(Integer id, String search, Float lat, Float lng) {
        this.id = id;
        this.search = search;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String getTABLE() {
        return TABLE;
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, getId());
        contentValues.put(COL_TITLE, getSearch());
        contentValues.put(COL_LAT, getLat());
        contentValues.put(COL_LNG, getLng());
        return contentValues;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }
}