package com.store.filter;

import com.store.annotation.Admin;
import com.store.dao.UserDAO;
import com.store.util.Constant;
import com.store.util.JWTProvider;
import io.jsonwebtoken.Claims;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Admin
public class AdminFilter implements ContainerRequestFilter
{
    @Context
    ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException
    {
        try {
            String authHeader = requestContext.getHeaderString(Constant.AUTH_HEADER);
            if (authHeader == null || !authHeader.startsWith(Constant.AUTH_BEARER)) {
                throw new Exception();
            }

            String token = authHeader.substring(Constant.AUTH_BEARER.length());

            if (!JWTProvider.validateJwtToken(token)) {
                throw new Exception();
            }
            Claims claims = JWTProvider.getClaimsFromJwtToken(token);

            String username = (String)claims.get("unique_name");
            //TODO: check role by using username
            String role = (String)claims.get("role");
            if(!UserDAO.checkIfUsernameExist(username)) throw new Exception();
            if(!role.equals("admin")) throw new Exception();
        }
        catch (Exception ex) {
            Response.ResponseBuilder builder = null;
            builder = Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized");
            throw new WebApplicationException(builder.build());
        }
    }
}
