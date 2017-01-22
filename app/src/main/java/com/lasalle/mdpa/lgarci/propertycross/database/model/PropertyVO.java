package com.lasalle.mdpa.lgarci.propertycross.database.model;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by FurruPi on 15/1/17.
 */

public class PropertyVO extends DataBaseModel implements Serializable{
    public static String TABLE = "Property";

    public static String COL_ID = "Id";
    public static String COL_TITLE = "Title";
    public static String COL_DESCRIPTION = "Description";
    public static String COL_ADRESS = "Adress";
    public static String COL_ZIPCODE = "ZipCode";
    public static String COL_CITY = "City";
    public static String COL_PRICE = "Price";
    public static String COL_SIZE = "Size";
    public static String COL_TYPE = "Type";
    public static String COL_MAIL = "Mail";
    public static String COL_PHONE = "Phone";
    public static String COL_LAT = "Lat";
    public static String COL_LNG = "Lng";
    public static String COL_DISTANCE = "Distance";
    public static String COL_FAVORITE = "Favorite";
    public static String COL_LAST_REFRESH = "LastRefresh";

    public static final String[] COLUMNS = { COL_ID, COL_TITLE, COL_DESCRIPTION, COL_ADRESS,
            COL_ZIPCODE, COL_CITY, COL_PRICE, COL_SIZE, COL_TYPE, COL_MAIL, COL_PHONE, COL_LAT,
            COL_LNG, COL_DISTANCE, COL_FAVORITE, COL_LAST_REFRESH };

    private String id;
    private String title;
    private String description;
    private String adress;
    private String zipcode;
    private String city;
    private String price;
    private String size;
    private String type;

    //Contact
    private String mail;
    private String phone;

    //Location
    private Float lat;
    private Float lng;
    private Float distance;

    private String favorite;
    private String lastRefresh;

    public PropertyVO() {}

    public PropertyVO(String id, String title, String description, String adress, String zipcode, String city, String price, String size, String type, String mail, String phone, Float lat, Float lng, Float distance, String favorite, String lastRefresh) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.adress = adress;
        this.zipcode = zipcode;
        this.city = city;
        this.price = price;
        this.size = size;
        this.type = type;
        this.mail = mail;
        this.phone = phone;
        this.lat = lat;
        this.lng = lng;
        this.distance = distance;
        this.favorite = favorite;
        this.lastRefresh = lastRefresh;
    }

    @Override
    public String getTABLE() {
        return TABLE;
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues content = new ContentValues();
        content.put(COL_ID, getId());
        content.put(COL_TITLE, getTitle());
        content.put(COL_DESCRIPTION, getDescription());
        content.put(COL_ADRESS, getAdress());
        content.put(COL_ZIPCODE, getZipcode());
        content.put(COL_CITY, getCity());
        content.put(COL_PRICE, getPrice());
        content.put(COL_SIZE, getSize());
        content.put(COL_TYPE, getType());
        content.put(COL_MAIL, getMail());
        content.put(COL_PHONE, getPhone());
        content.put(COL_LAT, getLat());
        content.put(COL_LNG, getLng());
        content.put(COL_DISTANCE, getDistance());
        content.put(COL_FAVORITE, getFavorite());
        content.put(COL_LAST_REFRESH, getLastRefresh());
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getLastRefresh() {
        return lastRefresh;
    }

    public void setLastRefresh(String lastRefresh) {
        this.lastRefresh = lastRefresh;
    }

}
