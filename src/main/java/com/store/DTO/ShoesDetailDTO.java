package com.store.DTO;

import java.util.List;

public class ShoesDetailDTO {

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
    private int salePrice;
    private List<String> images;
    private List<String> sizes;

    public ShoesDetailDTO() {
    }

    public ShoesDetailDTO(int id, String code, String name, String description, float rating, String styleName, String brandName, String genderName, int price, int isNew, int isOnSale, int salePrice, List<String> images, List<String> sizes) {
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
        this.salePrice = salePrice;
        this.images = images;
        this.sizes = sizes;
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

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }
}
