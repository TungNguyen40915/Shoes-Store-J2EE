package com.store.converter;

import com.store.dao.ShoesDAO;
import com.store.dao.ShoesImageDAO;
import com.store.dao.SizeDAO;
import com.store.dao.StockDAO;
import com.store.dto.CartItemDTO;
import com.store.model.CartItem;
import com.store.model.Shoes;
import com.store.model.Stock;

public class CartConverter {
    public static CartItemDTO ConvertCartItemEntityToCartItemDTO(CartItem item){
        CartItemDTO itemDTO = new CartItemDTO();

        Stock stock = StockDAO.getStockById(item.getStockID());
        Shoes shoes= ShoesDAO.getShoesById(stock.getShoesID());

        itemDTO.setName(shoes.getName());
        itemDTO.setQuantity(item.getAmount());
        itemDTO.setStockId(item.getStockID());
        itemDTO.setImage(ShoesImageDAO.getImage(shoes.getId()));
        itemDTO.setSizeName(SizeDAO.getSizeName(stock.getSizeID()));
        itemDTO.setShoesId(stock.getShoesID());

        if(shoes.getIsOnSale() == 1){
            //hanlde on sale price
            itemDTO.setPrice(shoes.getPrice()/2);
        }else itemDTO.setPrice(shoes.getPrice());

        return itemDTO;
    }
}
