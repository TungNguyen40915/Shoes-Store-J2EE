package com.store.controller.admin;

import com.store.dao.ShoesStyleDAO;
import com.store.model.ShoesType;
import com.store.model.Response;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/admin/shoestype")
public class AdminShoesTypeController {
    ObjectMapper mapper = new ObjectMapper();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAllTypes(){
        Response res = new Response();
        try {
            List<ShoesType> list = ShoesStyleDAO.getAll();
            res.setCode("OK");
            res.setMsg("Get Data Successfully");
            res.setTotalRecords(list.size());
            res.setData(mapper.writeValueAsString(list));
        } catch (IOException e) {
            res.setCode("FAIL");
            res.setMsg("Get Data failed");
            e.printStackTrace();
            res.setData("[]");
        }
        return res;
    }
}
