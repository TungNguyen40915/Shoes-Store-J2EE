package com.store.Converter;

import com.store.DAO.GenderDAO;
import com.store.DTO.CartItemDTO;
import com.store.DTO.CustomerDTO;
import com.store.model.CartItem;
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
}
