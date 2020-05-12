package com.store.Converter;

import com.store.DAO.*;
import com.store.DTO.ShoesDTO;
import com.store.model.Shoes;

public class ShoesConverter {

    public static ShoesDTO ConvertShoesEntityToShoesDTO(Shoes shoes){
        ShoesDTO shoesDTO = new ShoesDTO();
        shoesDTO.setId(shoes.getId());
        shoesDTO.setCode(shoes.getCode());
        shoesDTO.setName(shoes.getName());
        shoesDTO.setDescription(shoes.getDescription());
        shoesDTO.setRating(shoes.getRating());
        shoesDTO.setStyleName(String.valueOf(ShoesStyleDAO.getStyleName(shoes.getStyleID())));
        shoesDTO.setBrandName(String.valueOf(ShoesBrandDAO.getBrandName(shoes.getBrandID())));
        shoesDTO.setGenderName(String.valueOf(GenderDAO.getGenderName(shoes.getGenderID())));
        shoesDTO.setPrice(shoes.getPrice());
        shoesDTO.setIsNew(shoes.getIsNew());
        shoesDTO.setIsOnSale(shoes.getIsOnSale());
        shoesDTO.setImagePath(ShoesImageDAO.getImage(shoes.getId()));
        shoesDTO.setQuantity(StockDAO.getTotalQuantity(shoes.getId()));


        return shoesDTO;
    }
}
