package com.store.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "shoes_type")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShoesType {
    private int id;
    private String name ;
    private String description ;
    private int deleteFlag;

    public ShoesType() {
    }

    public ShoesType(int id, String name, String description, int deleteFlag) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
