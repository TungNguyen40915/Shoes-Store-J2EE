package com.store.DTO;

import java.util.Date;

public class ShoesDTO {
    private int id;
    private String name;
    private String description;
    private int price;
    private int isNew;
    private int isOnSale;
    private String imagePath;
    private int quantity;
    private int salePrice;
    private String styleName;

    public ShoesDTO() {
    }

    public ShoesDTO(int id, String name, String description, int price, int isNew, int isOnSale, String imagePath, int quantity, int salePrice, String styleName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isNew = isNew;
        this.isOnSale = isOnSale;
        this.imagePath = imagePath;
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.styleName = styleName;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }
}
