package com.store.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "shoes_brand")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShoesBrand {
    private int id;
    private String name ;
    private String country ;
    private int deleteFlag;

    public ShoesBrand() {
    }

    public ShoesBrand(int id, String name, String country, int deleteFlag) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.deleteFlag = deleteFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
