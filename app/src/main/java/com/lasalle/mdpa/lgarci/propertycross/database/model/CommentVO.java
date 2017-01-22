package com.lasalle.mdpa.lgarci.propertycross.database.model;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by FurruPi on 17/1/17.
 */

public class CommentVO extends DataBaseModel implements Serializable{
    public static final String TABLE = "Comment";

    public static String COL_ID = "Id";
    public static String COL_ID_PROPERTY = "IdProperty";
    public static String COL_USER = "User";
    public static String COL_DATE = "Date";
    public static String COL_COMMENT = "Comment";

    public static final String[] COLUMNS = { COL_ID, COL_ID_PROPERTY, COL_USER, COL_DATE, COL_COMMENT };

    private int id;
    private String propertyId;
    private String user;
    private String date;
    private String comment;

    public CommentVO() {}

    public CommentVO(int id, String propertyId, String user, String date, String comment) {
        this.id = id;
        this.propertyId = propertyId;
        this.user = user;
        this.date = date;
        this.comment = comment;
    }

    @Override
    public String getTABLE() {
        return TABLE;
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, getId());
        contentValues.put(COL_ID_PROPERTY, getPropertyId());
        contentValues.put(COL_USER, getUser());
        contentValues.put(COL_DATE, getDate());
        contentValues.put(COL_COMMENT, getComment());
        return contentValues;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
