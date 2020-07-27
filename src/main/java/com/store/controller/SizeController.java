package com.store.controller;

import com.store.dao.SizeDAO;
import com.store.model.Response;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/client/sizes")
public class SizeController {
    ObjectMapper mapper = new ObjectMapper();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAllSizes(){
        Response res = new Response();
        res.setCode("OK");
        res.setMsg("Get Data Successfully");
        try {
            List<String> list = SizeDAO.getAllSizes();
            res.setTotalRecords(list.size());
            res.setData(mapper.writeValueAsString(list));
        } catch (IOException e) {
            e.printStackTrace();
            res.setData("[]");
        }
        return res;
    }
}
