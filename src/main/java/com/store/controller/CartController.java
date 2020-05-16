package com.store.controller;

import com.store.Annotation.LoginRequired;
import com.store.Converter.CartConverter;
import com.store.Converter.ShoesConverter;
import com.store.DAO.CartDAO;
import com.store.DAO.ShoesDAO;
import com.store.DTO.CartItemDTO;
import com.store.DTO.ShoesDTO;
import com.store.model.CartItem;
import com.store.model.Response;
import com.store.model.Shoes;
import com.store.util.Constant;
import com.store.util.JWTProvider;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/cart")
public class CartController {

    ObjectMapper mapper = new ObjectMapper();

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addItemToCart(
            @FormParam("stockId") int stockId,
            @FormParam("quantity") int quantity,
            ContainerRequestContext requestContext) {
        String token = requestContext.getHeaderString(Constant.AUTH_HEADER).substring(Constant.AUTH_BEARER.length());
        String username = JWTProvider.getUserNameFromJwtToken(token);

        Response res = new Response();
        try {
            List<CartItem> items = new ArrayList<CartItem>();
            List<CartItemDTO> itemDTOS = new ArrayList<CartItemDTO>();
            res.setCode("OK");
            res.setMsg("Item inserted Successfully");
            items = CartDAO.addItemToCart(username,stockId,quantity);
            for (CartItem s: items) {
                itemDTOS.add(CartConverter.ConvertCartItemEntityToCartItemDTO(s));
            }
            res.setData(mapper.writeValueAsString(itemDTOS));
            res.setTotalRecords(items.size());

        } catch (IOException e) {
            e.printStackTrace();
            res.setCode("FAIL");
            res.setMsg("Something Went Wrong");
            res.setData("[]");
            res.setTotalRecords(0);
        }
        return res;
    }

    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateCartItem(
            @FormParam("stockId") int stockId,
            @FormParam("quantity") int quantity,
            ContainerRequestContext requestContext) {
        String token = requestContext.getHeaderString(Constant.AUTH_HEADER).substring(Constant.AUTH_BEARER.length());
        String username = JWTProvider.getUserNameFromJwtToken(token);

        Response res = new Response();
        try {
            List<CartItem> items = new ArrayList<CartItem>();
            List<CartItemDTO> itemDTOS = new ArrayList<CartItemDTO>();
            res.setCode("OK");
            res.setMsg("Updated Successfully");
            items = CartDAO.updateCart(username,stockId,quantity);
            for (CartItem s: items) {
                itemDTOS.add(CartConverter.ConvertCartItemEntityToCartItemDTO(s));
            }
            res.setData(mapper.writeValueAsString(itemDTOS));
            res.setTotalRecords(items.size());

        } catch (IOException e) {
            e.printStackTrace();
            res.setCode("FAIL");
            res.setMsg("Something Went Wrong");
            res.setData("[]");
            res.setTotalRecords(0);
        }
        return res;
    }

    @GET
    @Path("/get")
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getAllCartItems(ContainerRequestContext requestContext) {
        String token = requestContext.getHeaderString(Constant.AUTH_HEADER).substring(Constant.AUTH_BEARER.length());
        String username = JWTProvider.getUserNameFromJwtToken(token);

        Response res = new Response();
        res.setCode("OK");
        res.setMsg("Retrieve Data Successfully");
        try {
            List<CartItem> items = new ArrayList<CartItem>();
            List<CartItemDTO> itemDTOS = new ArrayList<CartItemDTO>();
            int cartId = CartDAO.getCartIdByUserName(username);
            items = CartDAO.getAllCartItemsByCartId(cartId);
            for (CartItem s: items) {
                itemDTOS.add(CartConverter.ConvertCartItemEntityToCartItemDTO(s));
            }
            res.setData(mapper.writeValueAsString(itemDTOS));
            res.setTotalRecords(items.size());

        } catch (IOException e) {
            e.printStackTrace();
            res.setData("[]");
            res.setTotalRecords(0);
        }
        return res;
    }
}
