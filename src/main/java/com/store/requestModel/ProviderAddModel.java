package com.store.requestModel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class ProviderAddModel {
    private String name;

    public ProviderAddModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
