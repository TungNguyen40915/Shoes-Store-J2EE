package com.store.controller;

import com.store.DAO.UserDAO;
import com.store.util.JWTProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.store.model.Response;

@Path("/authentication")
public class Authentication {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)

    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password) {

        try {
            authenticate(username, password);
            String token = issueToken(username);
            return new Response("200","Login successfully","AuthorizedToken: " + token,1);

        } catch (Exception e) {
            return new Response("403","Login failed","AuthorizedToken: null",0);
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
