package com.store.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "shoes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Shoes {
    private int id;
    private String code;
    private String name;
    private String description;
    private float rating;
    private int ratingCount;
    private int styleID;
    private int brandID;
    private int genderID;
    private int price;
    private int isNew;
    private int isOnSale;
    private Date createDate;
    private int deleteFlag;

    public Shoes() {
    }

    public Shoes(int id, String code, String name, String description, float rating, int ratingCount, int styleID, int brandID, int genderID, int price, int isNew, int isOnSale, Date createDate, int deleteFlag) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.styleID = styleID;
        this.brandID = brandID;
        this.genderID = genderID;
        this.price = price;
        this.isNew = isNew;
        this.isOnSale = isOnSale;
        this.createDate = createDate;
        this.deleteFlag = deleteFlag;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getStyleID() {
        return styleID;
    }

    public void setStyleID(int styleID) {
        this.styleID = styleID;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public int getGenderID() {
        return genderID;
    }

    public void setGenderID(int genderID) {
        this.genderID = genderID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    public int getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(int isOnSale) {
        this.isOnSale = isOnSale;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
