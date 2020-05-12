package com.store.controller;

import com.store.Converter.ShoesConverter;
import com.store.DAO.ShoesBrandDAO;
import com.store.DAO.ShoesDAO;
import com.store.model.Response;
import com.store.model.Shoes;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/brands")
public class BrandController {

    ObjectMapper mapper = new ObjectMapper();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAllBrands(){
        Response res = new Response();
        res.setCode("OK");
        res.setMsg("Get Data Successfully");
        try {
            List<String> list = ShoesBrandDAO.getAllBrands();
            res.setTotalRecords(list.size());
            res.setData(mapper.writeValueAsString(list));
        } catch (IOException e) {
            e.printStackTrace();
            res.setData("[]");
        }
        return res;
    }
}
