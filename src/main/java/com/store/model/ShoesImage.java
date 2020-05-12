package com.store.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "shoes_image")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShoesImage {
    private int id;
    private int shoesID;
    private String imagePath;
    private int deleteFlag;

    public ShoesImage() {
    }

    public ShoesImage(int id, int shoesID, String imagePath, int deleteFlag) {
        this.id = id;
        this.shoesID = shoesID;
        this.imagePath = imagePath;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
