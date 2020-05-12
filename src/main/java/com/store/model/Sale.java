package com.store.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sale {
    private int id;
    private String saleType;
    private int amount;
    private Date startDate;
    private Date expiredDate;
    private String status;
    private int deleteFlag;

    public Sale() {
    }

    public Sale(int id, String saleType, int amount, Date startDate, Date expiredDate, String status, int deleteFlag) {
        this.id = id;
        this.saleType = saleType;
        this.amount = amount;
        this.startDate = startDate;
        this.expiredDate = expiredDate;
        this.status = status;
        this.deleteFlag = deleteFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
