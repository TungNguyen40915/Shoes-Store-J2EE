package com.store.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "orderdetail")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDetail {
    private int id;
    private int shoesDetailId;
    private int orderId;
    private int quantity;
    private int totalCost;

    private ShoesDetail shoesDetail;
    private Order order;

    public OrderDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShoesDetailId() {
        return shoesDetailId;
    }

    public void setShoesDetailId(int shoesDetailId) {
        this.shoesDetailId = shoesDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public ShoesDetail getShoesDetail() {
        return shoesDetail;
    }

    public void setShoesDetail(ShoesDetail shoesDetail) {
        this.shoesDetail = shoesDetail;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
