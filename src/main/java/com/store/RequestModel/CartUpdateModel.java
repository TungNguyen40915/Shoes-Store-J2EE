package com.store.RequestModel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class CartUpdateModel {
    private int stockId;
    private int quantity;

    public CartUpdateModel() {
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
