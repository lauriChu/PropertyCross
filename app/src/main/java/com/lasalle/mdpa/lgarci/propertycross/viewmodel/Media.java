package com.lasalle.mdpa.lgarci.propertycross.viewmodel;

/**
 * Created by FurruPi on 17/1/17.
 */

public class Media {
    public static final String MEDIA_TYPE_IMAGE = "MediaTypeImage";
    public static final String MEDIA_TYPE_VIDEO = "MediaTypeVideo";

    private String path;
    private String idProperty;
    private String type;
    private String value;

    public Media(String path, String idProperty, String type, String value) {
        this.path = path;
        this.idProperty = idProperty;
        this.type = type;
        this.value = value;
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
