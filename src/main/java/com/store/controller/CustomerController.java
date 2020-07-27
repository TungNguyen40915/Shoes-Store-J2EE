package com.store.controller;


import com.store.annotation.LoginRequired;
import com.store.converter.CustomerConverter;
import com.store.dao.OrderDAO;
import com.store.requestModel.AddressAddModel;
import com.store.requestModel.CustomerUpdateModel;
import com.store.requestModel.NewCustomerModel;
import com.store.requestModel.UpdateAddressModel;
import com.store.model.Address;
import com.store.model.Customer;
import com.store.dao.CustomerDAO;
import com.store.model.Response;
import com.store.util.Constant;
import com.store.util.JWTProvider;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/client/customer")
public class CustomerController {

    ObjectMapper mapper = new ObjectMapper();

    @GET
    @LoginRequired
    @Path("/getInfo")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getCustomerInformation(@Context ContainerRequestContext requestContext) {
        String token = requestContext.getHeaderString(Constant.AUTH_HEADER).substring(Constant.AUTH_BEARER.length());
        String username = JWTProvider.getUserNameFromJwtToken(token);
        Response res = new Response();
        res.setCode("OK");
        res.setMsg("Get Data Successfully");
        try {
            Customer customer = CustomerDAO.getCustomerInfomationByUsername(username);
            res.setTotalRecords(1);
            res.setData(mapper.writeValueAsString(CustomerConverter.ConvertCustomerEntityToCustomerDTO(customer)));
        } catch (IOException e) {
            e.printStackTrace();
            res.setTotalRecords(0);
            res.setData("{}");
        }
        return res;
    }

    @POST
    @LoginRequired
    @Path("/updateInfo")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response updateCustomerInformation(
            CustomerUpdateModel customerUpdateModel,
            @Context ContainerRequestContext requestContext
    ) {
        String token = requestContext.getHeaderString(Constant.AUTH_HEADER).substring(Constant.AUTH_BEARER.length());
        String username = JWTProvider.getUserNameFromJwtToken(token);
        Response res = new Response();
        res.setCode("OK");
        res.setMsg("Get Data Successfully");
        try {
            Customer customer = CustomerDAO.updateCustomerInfomation(username,customerUpdateModel);
            res.setTotalRecords(1);
            res.setData(mapper.writeValueAsString(CustomerConverter.ConvertCustomerEntityToCustomerDTO(customer)));
        } catch (IOException e) {
            e.printStackTrace();
            res.setTotalRecords(0);
            res.setData("{}");
        }
        return res;
    }

    @POST
    @Path("/register")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response registerCustomer(NewCustomerModel newCustomerModel) throws Exception {
        Response res = new Response();

        try {
            res.setCode("OK");
            res.setMsg("Get Data Successfully");
            Customer customer = CustomerDAO.registerCustomer(newCustomerModel);
            res.setTotalRecords(1);
            res.setData(mapper.writeValueAsString(CustomerConverter.ConvertCustomerEntityToCustomerDTO(customer)));
        } catch (IOException e) {
            e.printStackTrace();
            res.setTotalRecords(0);
            res.setData("{}");
        }
        return res;
    }

    //TO-DO
    @GET
    @LoginRequired
    @Path("/changePassword")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response changePassword() {
        Response res = new Response();
        res.setCode("OK");
        res.setMsg("Get Data Successfully");
        try {
            res.setTotalRecords(1);
            res.setData(mapper.writeValueAsString(new Customer()));
        } catch (IOException e) {
            e.printStackTrace();
            res.setTotalRecords(0);
            res.setData("{}");
        }
        return res;
    }

    @GET
    @Path("/getAddresses")
    @LoginRequired
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getCustomerAddresses(@Context ContainerRequestContext requestContext) {
        String token = requestContext.getHeaderString(Constant.AUTH_HEADER).substring(Constant.AUTH_BEARER.length());
        String username = JWTProvider.getUserNameFromJwtToken(token);
        List<Address> list = OrderDAO.getCustomerAddressByUserName(username);
        Response res = new Response();
        res.setCode("OK");
        res.setMsg("Get Data Successfully");
        try {
            res.setTotalRecords(list.size());
            res.setData(mapper.writeValueAsString(list));
        } catch (IOException e) {
            e.printStackTrace();
            res.setTotalRecords(0);
            res.setData("[]");
        }
        return res;
    }

    @POST
    @LoginRequired
    @Path("/add-address")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response addAddress(
            AddressAddModel addressAddModel,
            @Context ContainerRequestContext requestContext
    ) {
        String token = requestContext.getHeaderString(Constant.AUTH_HEADER).substring(Constant.AUTH_BEARER.length());
        String username = JWTProvider.getUserNameFromJwtToken(token);
        List<Address> list = OrderDAO.addAddress(username, addressAddModel);
        Response res = new Response();
        res.setCode("OK");
        res.setMsg("Get Data Successfully");
        try {
            res.setTotalRecords(list.size());
            res.setData(mapper.writeValueAsString(list));
        } catch (IOException e) {
            e.printStackTrace();
            res.setTotalRecords(0);
            res.setData("[]");
        }
        return res;
    }

    @POST
    @LoginRequired
    @Path("/update-address")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response addAddress(
            UpdateAddressModel model,
            @Context ContainerRequestContext requestContext
    ) {
        String token = requestContext.getHeaderString(Constant.AUTH_HEADER).substring(Constant.AUTH_BEARER.length());
        String username = JWTProvider.getUserNameFromJwtToken(token);
        // TODO: check if this address belongs to this user
        List<Address> list = OrderDAO.updateAddress(username,model);
        Response res = new Response();
        res.setCode("OK");
        res.setMsg("Get Data Successfully");
        try {
            res.setTotalRecords(list.size());
            res.setData(mapper.writeValueAsString(list));
        } catch (IOException e) {
            e.printStackTrace();
            res.setTotalRecords(0);
            res.setData("[]");
        }
        return res;
    }






}
