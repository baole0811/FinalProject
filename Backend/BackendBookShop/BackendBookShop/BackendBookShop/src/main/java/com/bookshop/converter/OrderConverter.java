package com.bookshop.converter;

import com.bookshop.dto.OrderDTO;
import com.bookshop.model.Order;

public class OrderConverter {
    public static OrderDTO toModel(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setNameRecipient(order.getNameRecipient());
        orderDTO.setAddress(order.getAddress());
        orderDTO.setPhoneNumber(order.getPhoneNumber());
        orderDTO.setPayment(order.getPayment());
        orderDTO.setTotalMoney(order.getTotalPrice());
        orderDTO.setId(order.getId());
        orderDTO.setCreateBy(order.getCreateBy());
        orderDTO.setCreateDate(order.getCreateDate());
        orderDTO.setUserId(order.getUser().getId());
        return orderDTO;
    }
}
