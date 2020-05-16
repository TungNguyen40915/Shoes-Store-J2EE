package com.store.controller;


import com.store.model.Customer;
import com.store.DAO.CustomerDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/customers")
public class CustomerController {

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Customer> getCustomerList() {
        List<Customer> CustomerList = new ArrayList<Customer>();
        return CustomerList;
    }








}
