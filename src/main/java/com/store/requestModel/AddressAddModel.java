package com.store.requestModel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class AddressAddModel {
    private String city;
    private String district;
    private String ward;
    private String street;
    private String name;
    private String phoneNumber;

    public AddressAddModel() {
    }

    public AddressAddModel(String city, String district, String ward, String street) {
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
