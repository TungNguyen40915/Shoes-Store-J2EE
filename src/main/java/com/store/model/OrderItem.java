package com.store.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "order_item")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItem {
    private int id;
    private int orderID;
    private int stockID;
    private int amount;
    private int pricePerUnit;
    private int deleteFlag;

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public OrderItem(int id, int orderID, int stockID, int amount, int pricePerUnit, int deleteFlag) {
        this.id = id;
        this.orderID = orderID;
        this.stockID = stockID;
        this.amount = amount;
        this.pricePerUnit = pricePerUnit;
        this.deleteFlag = deleteFlag;
    }

    public OrderItem() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
