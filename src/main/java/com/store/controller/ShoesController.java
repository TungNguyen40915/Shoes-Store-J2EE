package com.store.controller;

import com.store.DAO.CustomerDAO;
import com.store.DAO.ShoesDAO;
import com.store.model.Customer;
import com.store.model.Shoes;
import com.store.security.Permission;
import com.store.security.Role;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/list-giay-noi-bac")
public class ShoesController {
    @GET
    @Permission(roles={Role.ADMIN})
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Shoes> getCustomerList() {
        List<Shoes> list = ShoesDAO.getAllShoes();
        return list;
    }
}
