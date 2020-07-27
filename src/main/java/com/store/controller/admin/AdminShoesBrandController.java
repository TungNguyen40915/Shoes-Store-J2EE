package com.store.controller.admin;

import com.store.dao.ShoesBrandDAO;
import com.store.model.Response;
import com.store.model.ShoesBrand;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/admin/shoesbrand")
public class AdminShoesBrandController {
    ObjectMapper mapper = new ObjectMapper();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAllBrands(){
        Response res = new Response();
        try {
            List<ShoesBrand> list = ShoesBrandDAO.getAll();
            res.setCode("200");
            res.setMsg("Success");
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
