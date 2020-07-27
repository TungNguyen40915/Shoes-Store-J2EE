package com.store.converter;

import com.store.dao.*;
import com.store.dto.ShoesDTO;
import com.store.dto.ShoesDetailDTO;
import com.store.model.Sale;
import com.store.model.Shoes;

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
            Sale sale = SaleDAO.getSaleByShoesId(shoes.getId());
            if(sale != null){
                if(sale.getSaleType().equals("AMOUNT"))
                    shoesDTO.setSalePrice(shoes.getPrice() - sale.getAmount());
                else if(sale.getSaleType().equals("PERCENT"))
                    shoesDTO.setSalePrice(shoes.getPrice() * sale.getAmount() / 100);
            }
            else shoesDTO.setSalePrice(shoes.getPrice());
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
            Sale sale = SaleDAO.getSaleByShoesId(shoes.getId());
            if(sale != null){
                if(sale.getSaleType().equals("AMOUNT"))
                    shoesDetailDTO.setSalePrice(shoes.getPrice() - sale.getAmount());
                else if(sale.getSaleType().equals("PERCENT"))
                    shoesDetailDTO.setSalePrice(shoes.getPrice() * sale.getAmount() / 100);
            }
            else shoesDetailDTO.setSalePrice(shoes.getPrice());
        }
        return shoesDetailDTO;
    }
}
