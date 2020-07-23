package com.store.controller;

import com.store.Annotation.LoginRequired;
import com.store.Converter.CartConverter;
import com.store.Converter.ShoesConverter;
import com.store.DAO.CartDAO;
import com.store.DAO.ShoesDAO;
import com.store.DTO.CartItemDTO;
import com.store.DTO.ShoesDTO;
import com.store.DTO.UpdateCartItem;
import com.store.RequestModel.ShoesRatingModel;
import com.store.model.CartItem;
import com.store.model.Response;
import com.store.model.Shoes;
import com.store.util.Constant;
import com.store.util.JWTProvider;
import com.store.util.Util;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/client/shoes")
public class ShoesController {

    ObjectMapper mapper = new ObjectMapper();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getShoes(
            @QueryParam("gender") String gender,
            @QueryParam("new") String isNew,
            @QueryParam("sale") String sale,
            @QueryParam("size") String size,
            @QueryParam("page") String page,
            @QueryParam("style") String style,
            @QueryParam("brand") String brand,
            @QueryParam("search") String search,
            @QueryParam("page-size") String pageSize,
            @QueryParam("price-range") String priceRange
            //1: < 1M
            //2: 1M - 3M
            //3: 3M - 5M
            //4: 5M - 10M
            //5: > 10M

    ) {
        Response res = new Response();
        res.setCode("OK");
        res.setMsg("Retrieve Data Successfully");
        try {
            List<Shoes> shoes = new ArrayList<Shoes>();
            List<ShoesDTO> shoesDTOS = new ArrayList<ShoesDTO>();
            shoes = ShoesDAO.getAllShoes(gender,isNew,sale,size,page,style,brand,search, pageSize, priceRange);
            for (Shoes s: shoes) {
                shoesDTOS.add(ShoesConverter.ConvertShoesEntityToShoesDTO(s));
            }
            res.setData(mapper.writeValueAsString(shoesDTOS));
            res.setTotalRecords(ShoesDAO.getTotalRecords(gender,isNew,sale,size,null,style,brand,search, pageSize, priceRange));

        } catch (IOException e) {
            e.printStackTrace();
            res.setData("[]");
        }
        return res;
    }


    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getShoesById(@PathParam("id") int id){
        Response res = new Response();
        res.setCode("OK");
        res.setMsg("Get Data Successfully");
        try {
            Shoes shoes = ShoesDAO.getShoesById(id);
            if (shoes == null) {
                res.setTotalRecords(0);
                res.setData("{}");
            }
            else{
                res.setTotalRecords(1);
                res.setData(mapper.writeValueAsString(ShoesConverter.ConvertShoesEntityToShoesDetailDTO(shoes)));
            }
        } catch (IOException e) {
            e.printStackTrace();
            res.setData("[]");
        }
        return res;
    }

    @POST
    @LoginRequired
    @Path("/rating")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCartItem(
            ShoesRatingModel model) {

        Response res = new Response();
        try {
            res.setCode("OK");
            res.setMsg("Updated Successfully");
            Shoes shoes = ShoesDAO.ratingShoes(model.getShoesId(),model.getRating());
            res.setData(mapper.writeValueAsString(ShoesConverter.ConvertShoesEntityToShoesDetailDTO(shoes)));
            res.setTotalRecords(0);
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
