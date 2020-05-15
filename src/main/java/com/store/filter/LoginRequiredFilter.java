package com.store.filter;

import com.store.Annotation.LoginRequired;
import com.store.util.Constant;
import com.store.util.JWTProvider;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@LoginRequired
public class LoginRequiredFilter implements ContainerRequestFilter
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

            String username = JWTProvider.getUserNameFromJwtToken(token);

            //check if username is exist here, just to make sure
        }
        catch (Exception ex) {
            Response.ResponseBuilder builder = null;
            builder = Response.status(Response.Status.UNAUTHORIZED).entity("Please login");
            throw new WebApplicationException(builder.build());
        }
    }
}
