package com.store.requestModel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class CustomerUpdateModel {

    private String name;
    private String dateOfBirth;
    private String email;
    private String phoneNumber;
    private String gender;

    public CustomerUpdateModel() {
    }

    public CustomerUpdateModel(String name, String dateOfBirth, String email, String phoneNumber, String gender) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
