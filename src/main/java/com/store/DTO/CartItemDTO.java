package com.store.DTO;

public class CartItemDTO {
    private int stockId;
    private int shoesId;
    private String name;
    private String sizeName;
    private int quantity;
    private int price;
    private String image;

    public CartItemDTO() {
    }

    public CartItemDTO(int stockId, int shoesId, String name, String sizeName, int quantity, int price, String image) {
        this.stockId = stockId;
        this.shoesId = shoesId;
        this.name = name;
        this.sizeName = sizeName;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public int getShoesId() {
        return shoesId;
    }

    public void setShoesId(int shoesId) {
        this.shoesId = shoesId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
