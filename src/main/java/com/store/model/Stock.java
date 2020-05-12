package com.store.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "provider")
@XmlAccessorType(XmlAccessType.FIELD)
public class Stock {
    private int id;
    private int shoesID;
    private int sizeID;
    private int instock;
    private int deleteFlag;

    public Stock() {
    }

    public Stock(int id, int shoesID, int sizeID, int instock, int deleteFlag) {
        this.id = id;
        this.shoesID = shoesID;
        this.sizeID = sizeID;
        this.instock = instock;
        this.deleteFlag = deleteFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShoesID() {
        return shoesID;
    }

    public void setShoesID(int shoesID) {
        this.shoesID = shoesID;
    }

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }

    public int getInstock() {
        return instock;
    }

    public void setInstock(int instock) {
        this.instock = instock;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
