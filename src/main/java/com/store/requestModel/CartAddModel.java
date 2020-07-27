package com.store.requestModel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class CartAddModel {

    private int shoesId;
    private String sizeName;
    private int quantity;

    public CartAddModel() {
    }

    public int getShoesId() {
        return shoesId;
    }

    public void setShoesId(int shoesId) {
        this.shoesId = shoesId;
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
}
