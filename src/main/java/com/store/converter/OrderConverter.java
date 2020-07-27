package com.store.converter;

import com.store.dao.*;
import com.store.dto.CartItemDTO;
import com.store.dto.OrderDTO;
import com.store.model.CustomerOrder;
import com.store.model.OrderItem;
import com.store.model.Shoes;

import java.util.ArrayList;
import java.util.List;

public class OrderConverter {
    public static OrderDTO ConvertOrderEntityToOrderDTO(CustomerOrder order){
        OrderDTO orderDTO = new OrderDTO();
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerID(order.getCustomerID());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setConfirmDate(order.getConfirmDate());
        orderDTO.setDeliveryDate(order.getDeliveryDate());
        orderDTO.setSaleID(order.getSaleID());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setPaymentStatus(order.getPaymentStatus());
        orderDTO.setDeliveryAddress(order.getDeliveryAddress());
        orderDTO.setRecipientName(order.getRecipientName());
        orderDTO.setRecipientPhoneNumber(order.getRecipientPhoneNumber());
        List<CartItemDTO> cartItemDTOS = convertOrderItemToCartItem(OrderDAO.getOrderItemsByOrderId(order.getId()));
        orderDTO.setCartItemDTOList(cartItemDTOS);

        orderDTO.setTotal(0);

        for (CartItemDTO item: cartItemDTOS) {
            orderDTO.setTotal(order.getTotal() + item.getPrice()*item.getQuantity());
        }

        return orderDTO;
    }

    private static List<CartItemDTO> convertOrderItemToCartItem(List<OrderItem> list){
        List<CartItemDTO> cartItemDTOS = new ArrayList<CartItemDTO>();

        for (OrderItem item: list) {
            Shoes shoes = ShoesDAO.getShoesById(StockDAO.getStockById(item.getOrderID()).getShoesID());
            CartItemDTO cartItem =  new CartItemDTO();
            cartItem.setStockId(item.getStockID());
            cartItem.setSizeName(SizeDAO.getSizeName(StockDAO.getStockById(item.getOrderID()).getSizeID()));
            cartItem.setQuantity(item.getAmount());
            cartItem.setPrice(item.getPricePerUnit());
            cartItem.setShoesId(shoes.getId());
            cartItem.setName(shoes.getName());
            cartItem.setImage(ShoesImageDAO.getAllImagesById(shoes.getId()).get(0));
            cartItemDTOS.add(cartItem);
        }
        return cartItemDTOS;
    }
}
