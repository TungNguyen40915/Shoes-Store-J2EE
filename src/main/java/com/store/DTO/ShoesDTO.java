package com.store.DTO;

import java.util.Date;

public class ShoesDTO {
    private int id;
    private String code;
    private String name;
    private String description;
    private float rating;
    private String styleName;
    private String brandName;
    private String genderName;
    private int price;
    private int isNew;
    private int isOnSale;
    private String imagePath;
    private int quantity;

    public ShoesDTO() {
    }

    public ShoesDTO(int id, String code, String name, String description, float rating, String styleName, String brandName, String genderName, int price, int isNew, int isOnSale, String imagePath, int quantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.styleName = styleName;
        this.brandName = brandName;
        this.genderName = genderName;
        this.price = price;
        this.isNew = isNew;
        this.isOnSale = isOnSale;
        this.imagePath = imagePath;
        this.quantity = quantity;
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

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
