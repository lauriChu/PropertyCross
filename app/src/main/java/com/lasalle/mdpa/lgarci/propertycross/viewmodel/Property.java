package com.lasalle.mdpa.lgarci.propertycross.viewmodel;

import java.util.List;

/**
 * Created by FurruPi on 17/1/17.
 */

public class Property {

    public static final String PROPERTY_TYPE_RENT = "PropertyTypeRent";
    public static final String PROPERTY_TYPE_SELL = "PropertyTypeSell";

    private String id;
    private String title;
    private String description;
    private String adress;
    private String zipcode;
    private String city;
    private String price;
    private String size;
    private String type;
    private List<Media> mediaList;
    private List<Comment> comments;

    public Property(String id, String title, String description, String adress, String zipcode, String city, String price, String size, String type, List<Media> mediaList, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.adress = adress;
        this.zipcode = zipcode;
        this.city = city;
        this.price = price;
        this.size = size;
        this.type = type;
        this.mediaList = mediaList;
        this.comments = comments;
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

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
