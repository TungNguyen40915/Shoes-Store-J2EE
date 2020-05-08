package com.store.security;

import com.store.DAO.UserDAO;
import com.store.util.Constant;

import java.io.IOException;
import java.lang.reflect.Method;
import java.rmi.server.ExportException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Permission
public class AuthFilter implements ContainerRequestFilter
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

            if (!JwtProvider.validateJwtToken(token)) {
                throw new Exception();
            }

            String username = JwtProvider.getUserNameFromJwtToken(token);

            Method resourceMethod = resourceInfo.getResourceMethod();
            Permission annotation = resourceMethod.getAnnotation(com.store.security.Permission.class);

            List<Role> permittedRoles = Arrays.asList(annotation.roles());
            List<Role> userRoles = UserDAO.getUserRole(username);

            boolean isPermitted = false;
            for (Role role : permittedRoles){
                if (userRoles.contains(role))
                    isPermitted = true;
            }

            if (!isPermitted) throw new Exception();
        }
        catch (Exception ex) {
            Response.ResponseBuilder builder = null;
            String path = requestContext.getUriInfo().getRequestUri().getPath();
            UnauthorizedResponse res = new UnauthorizedResponse(path);
            builder = Response.status(Response.Status.UNAUTHORIZED).entity(res);
            throw new WebApplicationException(builder.build());
        }
    }
}
