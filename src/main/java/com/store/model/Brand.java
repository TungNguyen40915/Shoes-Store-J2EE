package com.store.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "brand")
@XmlAccessorType(XmlAccessType.FIELD)
public class Brand {
    private int id;
    private String name;
    private List<ShoesType> types;

    public Brand() {
    }

    public Brand(int id, String name, List<ShoesType> types) {
        this.id = id;
        this.name = name;
        this.types = types;
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

    public List<ShoesType> getTypes() {
        return types;
    }

    public void setTypes(List<ShoesType> types) {
        this.types = types;
    }
}
