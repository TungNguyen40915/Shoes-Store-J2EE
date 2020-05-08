package com.store.controller;

import com.store.DAO.UserDAO;
import com.store.payload.LoginQuestPayload;
import com.store.payload.LoginResponsePayload;
import com.store.security.JwtProvider;
import com.store.security.UnauthorizedResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/auth")
public class AuthController {

    @POST
    @Path("/signin")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON })
    public Response signIn(@Context UriInfo uriInfo, LoginQuestPayload payload) {
        String username = UserDAO.signIn(payload.getUsernameOrEmail(),payload.getPassword());
        if (username != null){
            String token = JwtProvider.generateJwtToken(username);
            LoginResponsePayload returnPayload = new LoginResponsePayload(token);
            return Response.status(Response.Status.OK).entity(returnPayload).build();
        }

        UnauthorizedResponse res = new UnauthorizedResponse("path");
        return Response.status(Response.Status.UNAUTHORIZED).entity(res).build();
    }
}
