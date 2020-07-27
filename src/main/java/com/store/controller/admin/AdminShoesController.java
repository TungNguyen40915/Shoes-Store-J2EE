package com.store.controller.admin;

import com.store.annotation.Admin;
import com.store.annotation.LoginRequired;
import com.store.converter.ShoesConverter;
import com.store.dao.AdminShoesDAO;
import com.store.dto.ShoesDTO;
import com.store.model.Response;
import com.store.model.Shoes;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/admin/shoes")
public class AdminShoesController {

    ObjectMapper mapper = new ObjectMapper();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Admin
    public Response getShoes(
            @QueryParam("page") int page,
            @QueryParam("page-size") int pageSize,
            @QueryParam("styleId") int style,
            @QueryParam("brandId") int brand,
            @QueryParam("search") String search,
            @QueryParam("price") int price,
            @QueryParam("minPrice") int minPrice,
            @QueryParam("maxPrice") int maxPrice
    ) {
        Response res = new Response();
        try {
            List<Shoes> shoes = new ArrayList<Shoes>();
            List<ShoesDTO> shoesDTOS = new ArrayList<ShoesDTO>();
            res.setCode("OK");
            res.setMsg("Retrieve Data Successfully");
            shoes = AdminShoesDAO.getAllShoes(page,pageSize,style,brand,search, price,minPrice,maxPrice);
            for (Shoes s: shoes) {
                shoesDTOS.add(ShoesConverter.ConvertShoesEntityToShoesDTO(s));
            }
            res.setData(mapper.writeValueAsString(shoesDTOS));
            res.setTotalRecords(AdminShoesDAO.getTotalRecords(page,pageSize,style,brand,search, price,minPrice,maxPrice));

        } catch (IOException e) {
            res.setCode("FAILED");
            res.setMsg("Retrieve Data failed");
            e.printStackTrace();
            res.setData("[]");
            res.setTotalRecords(0);
        }
        return res;
    }

    @GET
    @Path("/{id}")
    @LoginRequired
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getShoesById(@PathParam("id") int id){
        Response res = new Response();

        try {
            res.setCode("OK");
            res.setMsg("Get Data Successfully");
            Shoes shoes = AdminShoesDAO.getShoesById(id);
            if (shoes == null) {
                res.setTotalRecords(0);
                res.setData("{}");
            }
            else{
                res.setTotalRecords(1);
                res.setData(mapper.writeValueAsString(ShoesConverter.ConvertShoesEntityToShoesDetailDTO(shoes)));
            }
        } catch (IOException e) {
            res.setCode("FAIL");
            res.setMsg("Get Data Fail");
            e.printStackTrace();
            res.setData("[]");
        }
        return res;
    }
}
