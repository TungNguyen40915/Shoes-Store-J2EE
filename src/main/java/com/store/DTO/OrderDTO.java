package com.store.DTO;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private int id;
    private int customerID;
    private Date orderDate;
    private Date confirmDate;
    private Date deliveryDate;
    private int total;
    private int saleID;
    private String status;
    private String paymentStatus;
    private String deliveryAddress;
    private List<CartItemDTO> cartItemDTOList;

    public OrderDTO() {
    }

    public OrderDTO(int id, int customerID, Date orderDate, Date confirmDate, Date deliveryDate, int total, int saleID, String status, String paymentStatus, String deliveryAddress, List<CartItemDTO> cartItemDTOList) {
        this.id = id;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.confirmDate = confirmDate;
        this.deliveryDate = deliveryDate;
        this.total = total;
        this.saleID = saleID;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.deliveryAddress = deliveryAddress;
        this.cartItemDTOList = cartItemDTOList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<CartItemDTO> getCartItemDTOList() {
        return cartItemDTOList;
    }

    public void setCartItemDTOList(List<CartItemDTO> cartItemDTOList) {
        this.cartItemDTOList = cartItemDTOList;
    }
}
