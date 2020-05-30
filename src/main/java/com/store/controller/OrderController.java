package com.store.controller;

import com.store.Annotation.LoginRequired;
import com.store.Converter.OrderConverter;
import com.store.Converter.ShoesConverter;
import com.store.DAO.OrderDAO;
import com.store.DAO.ShoesDAO;
import com.store.DTO.OrderDTO;
import com.store.model.CustomerOrder;
import com.store.model.Response;
import com.store.model.Shoes;
import com.store.util.Constant;
import com.store.util.JWTProvider;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/order")
public class OrderController {
    ObjectMapper mapper = new ObjectMapper();

    @POST
    @LoginRequired
    @Produces(MediaType.APPLICATION_JSON)
    public Response processOrder(
            @QueryParam("addressId") int addressID,
            @Context ContainerRequestContext requestContext
    ) {
        String token = requestContext.getHeaderString(Constant.AUTH_HEADER).substring(Constant.AUTH_BEARER.length());
        String username = JWTProvider.getUserNameFromJwtToken(token);
        Response res = new Response();
        try {
            res.setCode("OK");
            res.setMsg("Updated Successfully");
            res.setData(mapper.writeValueAsString(OrderConverter.ConvertOrderEntityToOrderDTO(OrderDAO.handleOrder(username,addressID))));
        } catch (Exception e) {
            e.printStackTrace();
            res.setCode("FAIL");
            res.setMsg("Something Went Wrong");
            res.setData("[]");
            res.setTotalRecords(0);
        }
        return res;
    }


    @GET
    @LoginRequired
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderList(@Context ContainerRequestContext requestContext) {
        String token = requestContext.getHeaderString(Constant.AUTH_HEADER).substring(Constant.AUTH_BEARER.length());
        String username = JWTProvider.getUserNameFromJwtToken(token);
        Response res = new Response();
        List<OrderDTO> orderDTOList = new ArrayList<OrderDTO>();
        try {
            res.setCode("OK");
            res.setMsg("Updated Successfully");
            List<CustomerOrder> customerOrderList = OrderDAO.getOrderListByCustomer(username);
            for (CustomerOrder order : customerOrderList) {
                orderDTOList.add(OrderConverter.ConvertOrderEntityToOrderDTO(order));
            }

            res.setData(mapper.writeValueAsString(orderDTOList));
            res.setTotalRecords(orderDTOList.size());
        } catch (Exception e) {
            e.printStackTrace();
            res.setCode("FAIL");
            res.setMsg("Something Went Wrong");
            res.setData("[]");
            res.setTotalRecords(0);
        }
        return res;
    }

}
