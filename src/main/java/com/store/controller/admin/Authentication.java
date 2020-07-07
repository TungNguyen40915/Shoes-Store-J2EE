package com.store.controller.admin;

import com.store.Converter.CustomerConverter;
import com.store.DAO.CustomerDAO;
import com.store.DAO.UserDAO;
import com.store.DTO.AdminLoginResponse;
import com.store.RequestModel.LoginModel;
import com.store.model.Customer;
import com.store.model.User;
import com.store.util.JWTProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.codehaus.jackson.map.ObjectMapper;

@Path("/auth/login")
public class Authentication {
    ObjectMapper mapper = new ObjectMapper();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(LoginModel user) {
        ResponseBuilder builder = Response.status(Response.Status.OK);
        try {
            authenticate(user.getUsername(), user.getPassword());
            User info = UserDAO.getUserByUsername(user.getUsername());
            String role;
            if (info.getRoleID()==1)
                role="user";
            else role="admin";
            String token = issueToken(info.getId(),info.getUsername(),role);
            builder.entity(new AdminLoginResponse(token));
            return builder.build();
        } catch (Exception e) {
            builder.status(Response.Status.UNAUTHORIZED);
            builder.entity("Unauthorized");
            return builder.build();
        }
    }

    private void authenticate(String username, String password) throws Exception {
        String userLogin = UserDAO.signin(username,password);
        if("".equals(userLogin)) throw new Exception();
    }

    private String issueToken(int nameid, String unique_name, String role) {
        return JWTProvider.generateAdminJwtToken(nameid,unique_name,role);
    }
}
