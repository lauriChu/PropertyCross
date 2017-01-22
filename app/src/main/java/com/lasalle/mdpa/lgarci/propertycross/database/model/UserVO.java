package com.lasalle.mdpa.lgarci.propertycross.database.model;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by FurruPi on 15/1/17.
 */

public class UserVO extends DataBaseModel implements Serializable{
    public static final String TABLE = "User";

    public static String COL_ID = "Id";
    public static String COL_TOKEN = "Token";
    public static String COL_EMAIL = "Email";
    public static String COL_NAME = "Name";
    public static String COL_SURNAME = "Surname";
    public static String COL_PHOTO = "Photo";
    public static String COL_PHONE = "Phone";

    public static final String[] COLUMNS = { COL_ID, COL_TOKEN, COL_EMAIL, COL_NAME, COL_SURNAME,
            COL_PHOTO, COL_PHONE };

    private String id;
    private String token;
    private String email;
    private String name;
    private String surname;
    private String photo;
    private String phone;

    public UserVO(){}

    public UserVO(String id, String token, String email, String name, String surname, String photo, String phone) {
        this.id = id;
        this.token = token;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.photo = photo;
        this.phone = phone;
    }

    @Override
    public String getTABLE() {
        return TABLE;
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues result = new ContentValues();
        result.put(COL_ID, getId());
        result.put(COL_TOKEN, getToken());
        result.put(COL_EMAIL, getEmail());
        result.put(COL_NAME, getName());
        result.put(COL_SURNAME, getSurname());
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
