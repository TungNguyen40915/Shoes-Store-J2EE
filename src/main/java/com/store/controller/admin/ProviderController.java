package com.store.controller.admin;

import com.store.Converter.CustomerConverter;
import com.store.Converter.ProviderConverter;
import com.store.DAO.CustomerDAO;
import com.store.DAO.ProviderDAO;
import com.store.DAO.ShoesBrandDAO;
import com.store.RequestModel.LoginModel;
import com.store.RequestModel.ProviderAddModel;
import com.store.model.Customer;
import com.store.model.Provider;
import com.store.model.Response;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/provider")
public class ProviderController {
    ObjectMapper mapper = new ObjectMapper();

    //TODO: use annotation for admin role here
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProviders(){
        Response res = new Response();
        res.setCode("OK");
        res.setMsg("Success.");
        try {
            List<Provider> list = ProviderDAO.getAllProviders();
            res.setTotalRecords(list.size());
            res.setData(mapper.writeValueAsString(ProviderConverter.ConvertProviderEntityToProviderDTO(list)));
        } catch (IOException e) {
            e.printStackTrace();
            res.setData("[]");
        }
        return res;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(ProviderAddModel provider){
        Response res = new Response();
        res.setCode("OK");
        res.setMsg("Success.");
        try {
            ProviderDAO.insert(provider.getName());
            res.setTotalRecords(1);
            res.setData("...");
        } catch (Exception e) {
            e.printStackTrace();
            res.setData("...");
        }
        return res;
    }
}
