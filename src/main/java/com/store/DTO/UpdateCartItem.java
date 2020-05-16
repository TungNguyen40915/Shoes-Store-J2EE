package com.store.DTO;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UpdateCartItem {
    private int stockId;
    private int quantity;



    public UpdateCartItem() {
    }

    public UpdateCartItem(int stockId, int quantity) {
        this.stockId = stockId;
        this.quantity = quantity;
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