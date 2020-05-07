package com.store.controller;

import com.store.DAO.ShoesDAO;
import com.store.model.Shoes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/shoes-list")
public class ShoesListController {
    @GET
    @Path("/shoes-type/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Shoes> getByShoesType(@PathParam("id") int typeId) {
        List<Shoes> list = ShoesDAO.getShoesByTypeId(typeId);
        return list;
    }

    @GET
    @Path("/brand/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Shoes> getByBrand(@PathParam("id") int brandId) {
        List<Shoes> list = ShoesDAO.getShoesByBrandId(brandId);
        return list;
    }

    @GET
    @Path("/gender/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Shoes> getByGender(@PathParam("id") int genderId) {
        List<Shoes> list = ShoesDAO.getShoesByGenderId(genderId);
        return list;
    }

    @GET
    @Path("/price/{min}/{max}")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Shoes> getByPrice(@PathParam("min") int min,@PathParam("max") int max) {
        List<Shoes> list = ShoesDAO.getShoesByPrice(min,max);
        return list;
    }
}
