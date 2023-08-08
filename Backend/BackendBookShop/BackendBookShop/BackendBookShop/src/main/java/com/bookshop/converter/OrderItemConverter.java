package com.bookshop.converter;

import com.bookshop.dto.OrderItemDTO;
import com.bookshop.model.CartItem;
import com.bookshop.model.OrderItem;

public class OrderItemConverter {
    public static OrderItem toEntity(CartItem cartItem){
        OrderItem orderItem = new OrderItem();
        orderItem.setPriceItem(cartItem.getPriceItem());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setBook(cartItem.getBook());
        return orderItem;
    }
    public static OrderItemDTO toModel( OrderItem orderItem){
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setPrice(orderItem.getPriceItem());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setNameBook(orderItem.getBook().getName());
        return orderItemDTO;
    }
}
