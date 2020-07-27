package com.store.controller;

import com.store.dao.ShoesBrandDAO;
import com.store.model.Response;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/client/brands")
public class BrandController {

    ObjectMapper mapper = new ObjectMapper();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAllBrands(){
        Response res = new Response();
        try {
            List<String> list = ShoesBrandDAO.getAllBrands();
            res.setCode("OK");
            res.setMsg("Get Data Successfully");
            res.setTotalRecords(list.size());
            res.setData(mapper.writeValueAsString(list));
        } catch (IOException e) {
            e.printStackTrace();
            res.setCode("FAIL");
            res.setMsg("Get Data Failed");
            res.setData("[]");
            res.setTotalRecords(0);
        }
        return res;
    }
}
