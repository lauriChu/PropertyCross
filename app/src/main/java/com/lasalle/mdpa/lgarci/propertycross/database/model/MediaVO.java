package com.lasalle.mdpa.lgarci.propertycross.database.model;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by FurruPi on 15/1/17.
 */

public class MediaVO extends DataBaseModel implements Serializable{
    public static final String TABLE = "Media";

    public static String COL_Path = "Path";
    public static String COL_IdProperty = "IdProperty";
    public static String COL_IdComment = "IdComment";
    public static String COL_Type = "Type";
    public static String COL_Value = "Value";

    public static final String[] COLUMNS = { COL_Path, COL_IdProperty, COL_Type, COL_Value };

    private String path;
    private String idProperty;
    private Integer idComment;
    private String type;
    private String value;

    public MediaVO() {}

    public MediaVO(String path, String idProperty, Integer idComment, String type, String value) {
        this.path = path;
        this.idProperty = idProperty;
        this.idComment = idComment;
        this.type = type;
        this.value = value;
    }

    @Override
    public String getTABLE() {
        return TABLE;
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues result = new ContentValues();
        result.put(COL_Path, getPath());
        result.put(COL_IdProperty, getIdProperty());
        result.put(COL_IdComment, getIdComment());
        result.put(COL_Type, getType());
        result.put(COL_Value, getValue());
        return result;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(String idProperty) {
        this.idProperty = idProperty;
    }

    public Integer getIdComment() {
        return idComment;
    }

    public void setIdComment(Integer idComment) {
        this.idComment = idComment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
