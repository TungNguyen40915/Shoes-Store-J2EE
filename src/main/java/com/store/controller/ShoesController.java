package com.store.controller;

import com.store.Converter.ShoesConverter;
import com.store.DAO.ShoesDAO;
import com.store.DTO.ShoesDTO;
import com.store.model.Response;
import com.store.model.Shoes;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
            @QueryParam("search") String search
    ) {
        Response res = new Response();
        res.setCode("OK");
        res.setMsg("Retrieve Data Successfully");
        try {

            List<Shoes> shoes = new ArrayList<Shoes>();
            List<ShoesDTO> shoesDTOS = new ArrayList<ShoesDTO>();
            shoes = ShoesDAO.getAllShoes(gender,isNew,sale,size,page,style,brand,search);
            for (Shoes s: shoes) {
                shoesDTOS.add(ShoesConverter.ConvertShoesEntityToShoesDTO(s));
            }
            res.setData(mapper.writeValueAsString(shoesDTOS));
        } catch (IOException e) {
            e.printStackTrace();
            res.setData("[]");
        }
        return res;
    }
}
