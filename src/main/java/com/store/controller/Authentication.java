package com.store.controller;

import com.store.Converter.CustomerConverter;
import com.store.DAO.CustomerDAO;
import com.store.DAO.UserDAO;
import com.store.RequestModel.LoginModel;
import com.store.model.Customer;
import com.store.util.JWTProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.store.model.Response;
import org.codehaus.jackson.map.ObjectMapper;

@Path("/authentication")
public class Authentication {
    ObjectMapper mapper = new ObjectMapper();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(LoginModel user) {

        try {
            authenticate(user.getUsername(), user.getPassword());
            String token = issueToken(user.getUsername());
            Customer customer = CustomerDAO.getCustomerInfomationByUsername(user.getUsername());
            return new Response(
                    "200",
                    "Login successfully",
                    mapper.writeValueAsString(CustomerConverter.ConvertCustomerEntityToUserLogin(customer,token)),
                    1);

        } catch (Exception e) {
            return new Response(
                    "403",
                    "Login failed",
                    "AuthorizedToken: null",
                    0);
        }
    }

    private void authenticate(String username, String password) throws Exception {
        String userLogin = UserDAO.signin(username,password);
        if("".equals(userLogin)) throw new Exception();
    }

    private String issueToken(String username) {
        return JWTProvider.generateJwtToken(username);
    }
}
