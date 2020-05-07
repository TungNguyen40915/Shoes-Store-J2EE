package com.store.controller;

import com.store.DAO.BrandDAO;
import com.store.DAO.GenderDAO;
import com.store.DAO.ShoesTypeDAO;
import com.store.model.Brand;
import com.store.model.Gender;
import com.store.model.ShoesType;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/admin/data")
public class AdminDataController {

    @POST
    @Path("/get-gender")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Gender> getGenderList() {
        List<Gender> list = GenderDAO.getAllGenders();
        return list;
    }

    @POST
    @Path("/get-brand")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Brand> getBrandList() {
        List<Brand> list = BrandDAO.getAllBrands();
        return list;
    }

    @POST
    @Path("/get-brand-with-type")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Brand> getBrandsWithType() {
        List<Brand> list = BrandDAO.getAllBrandsWithType();
        return list;
    }

    @POST
    @Path("/get-shoestype")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<ShoesType> getShoesTypeList() {
        List<ShoesType> list = ShoesTypeDAO.getAllShoesTypes();
        return list;
    }
}
