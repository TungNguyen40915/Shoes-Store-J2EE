package com.store.controller;

import com.store.Converter.CustomerConverter;
import com.store.DAO.CustomerDAO;
import com.store.DAO.UserDAO;
import com.store.DTO.AdminLoginResponse;
import com.store.RequestModel.LoginModel;
import com.store.model.Customer;
import com.store.util.JWTProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

@Path("/client/authentication")
public class Authentication {
    ObjectMapper mapper = new ObjectMapper();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(LoginModel user) {
        Response.ResponseBuilder builder = Response.status(Response.Status.OK);
        try {
            authenticate(user.getUsername(), user.getPassword());
            String token = issueToken(user.getUsername());
            Customer customer = CustomerDAO.getCustomerInfomationByUsername(user.getUsername());
            com.store.model.Response entity = new com.store.model.Response("200",
                    "Login successfully",
                    mapper.writeValueAsString(CustomerConverter.ConvertCustomerEntityToUserLogin(customer,token)),
                    1);
            builder.entity(entity);
            return builder.build();
        } catch (Exception e) {
            com.store.model.Response entity = new com.store.model.Response(
                    "403",
                    "Login failed",
                    "AuthorizedToken: null",
                    0);
            builder.status(Response.Status.UNAUTHORIZED);
            builder.entity(entity);
            return builder.build();
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
