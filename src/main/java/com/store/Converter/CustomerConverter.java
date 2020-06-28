package com.store.Converter;

import com.store.DAO.GenderDAO;
import com.store.DTO.CustomerDTO;
import com.store.DTO.UserLogin;
import com.store.model.Customer;

public class CustomerConverter {

    public static CustomerDTO ConvertCustomerEntityToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setDateOfBirth(customer.getDateOfBirth());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setGender(GenderDAO.getGenderName(customer.getGenderID()));
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setName(customer.getName());
        return customerDTO;
    }

    public static UserLogin ConvertCustomerEntityToUserLogin(Customer customer, String token){
        UserLogin userLogin = new UserLogin();
        userLogin.setId(customer.getId());
        userLogin.setDateOfBirth(customer.getDateOfBirth());
        userLogin.setEmail(customer.getEmail());
        userLogin.setGender(GenderDAO.getGenderName(customer.getGenderID()));
        userLogin.setPhoneNumber(customer.getPhoneNumber());
        userLogin.setName(customer.getName());
        userLogin.setAuthorizedToken(token);
        return userLogin;
    }

}
