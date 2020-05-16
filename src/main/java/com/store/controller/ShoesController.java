package com.store.controller;

import com.store.Annotation.LoginRequired;
import com.store.Converter.ShoesConverter;
import com.store.DAO.ShoesDAO;
import com.store.DTO.ShoesDTO;
import com.store.model.Response;
import com.store.model.Shoes;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/shoes")
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
            @QueryParam("size") String pageSize
    ) {
        Response res = new Response();
        res.setCode("OK");
        res.setMsg("Retrieve Data Successfully");
        try {
            List<Shoes> shoes = new ArrayList<Shoes>();
            List<ShoesDTO> shoesDTOS = new ArrayList<ShoesDTO>();
            shoes = ShoesDAO.getAllShoes(gender,isNew,sale,size,page,style,brand,search, pageSize);
            for (Shoes s: shoes) {
                shoesDTOS.add(ShoesConverter.ConvertShoesEntityToShoesDTO(s));
            }
            res.setData(mapper.writeValueAsString(shoesDTOS));
            res.setTotalRecords(ShoesDAO.getTotalRecords(gender,isNew,sale,size,page,style,brand,search, pageSize));

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
}
