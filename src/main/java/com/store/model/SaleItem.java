package com.store.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sale_item")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleItem {
    private int id;
    private int saleID;
    private int stockID;
    private int deleteFlag;

    public SaleItem() {
    }

    public SaleItem(int id, int saleID, int stockID, int deleteFlag) {
        this.id = id;
        this.saleID = saleID;
        this.stockID = stockID;
        this.deleteFlag = deleteFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
