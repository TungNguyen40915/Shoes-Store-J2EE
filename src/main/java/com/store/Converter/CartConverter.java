package com.store.Converter;

import com.store.DAO.ShoesDAO;
import com.store.DAO.ShoesImageDAO;
import com.store.DAO.StockDAO;
import com.store.DTO.CartItemDTO;
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

        if(shoes.getIsOnSale() == 1){
            //hanlde on sale price
            itemDTO.setPrice(shoes.getPrice()/2);
        }else itemDTO.setPrice(shoes.getPrice());

        return itemDTO;
    }
}
