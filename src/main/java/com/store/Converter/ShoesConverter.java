package com.store.Converter;

import com.store.DAO.*;
import com.store.DTO.ShoesDTO;
import com.store.DTO.ShoesDetailDTO;
import com.store.model.Shoes;
import com.store.model.ShoesImage;

public class ShoesConverter {

    public static ShoesDTO ConvertShoesEntityToShoesDTO(Shoes shoes){
        ShoesDTO shoesDTO = new ShoesDTO();
        shoesDTO.setId(shoes.getId());
        shoesDTO.setName(shoes.getName());
        shoesDTO.setDescription(shoes.getDescription());
        shoesDTO.setPrice(shoes.getPrice());
        shoesDTO.setIsNew(shoes.getIsNew());
        shoesDTO.setIsOnSale(shoes.getIsOnSale());
        shoesDTO.setImagePath(ShoesImageDAO.getImage(shoes.getId()));
        shoesDTO.setQuantity(StockDAO.getTotalQuantity(shoes.getId()));
        shoesDTO.setStyleName(ShoesStyleDAO.getStyleName(shoes.getStyleID()));

        if(shoes.getIsOnSale() == 0) shoesDTO.setSalePrice(shoes.getPrice());
        else {
            //hanle sale
            shoesDTO.setSalePrice(0);
        }

        return shoesDTO;
    }

    public static ShoesDetailDTO ConvertShoesEntityToShoesDetailDTO(Shoes shoes){
        ShoesDetailDTO shoesDetailDTO = new ShoesDetailDTO();
        shoesDetailDTO.setId(shoes.getId());
        shoesDetailDTO.setCode(shoes.getCode());
        shoesDetailDTO.setName(shoes.getName());
        shoesDetailDTO.setDescription(shoes.getDescription());
        shoesDetailDTO.setPrice(shoes.getPrice());
        shoesDetailDTO.setRating(shoes.getRating());
        shoesDetailDTO.setStyleName(String.valueOf(ShoesStyleDAO.getStyleName(shoes.getStyleID())));
        shoesDetailDTO.setBrandName(String.valueOf(ShoesBrandDAO.getBrandName(shoes.getBrandID())));
        shoesDetailDTO.setGenderName(String.valueOf(GenderDAO.getGenderName(shoes.getGenderID())));
        shoesDetailDTO.setIsNew(shoes.getIsNew());
        shoesDetailDTO.setIsOnSale(shoes.getIsOnSale());
        shoesDetailDTO.setSizes(StockDAO.getAllSizeByShoesID(shoes.getId()));
        shoesDetailDTO.setImages(ShoesImageDAO.getAllImagesById(shoes.getId()));
        shoesDetailDTO.setRatingCount(shoes.getRatingCount());
        if(shoes.getIsOnSale() == 0) shoesDetailDTO.setSalePrice(shoes.getPrice());
        else {
            //hanle sale
            shoesDetailDTO.setSalePrice(0);
        }


        return shoesDetailDTO;
    }
}
