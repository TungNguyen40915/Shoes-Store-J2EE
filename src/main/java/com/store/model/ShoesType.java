package com.store.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "shoestype")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShoesType {
    private int id;
    private String name;
    private int brandId;

    private Brand brand;

    public ShoesType() {
    }

    public ShoesType(int id, String name, int brandId, Brand brand) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.brand = brand;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }
}
