package com.bookshop.service;

import com.bookshop.dto.OrderItemDTO;

import java.util.List;

public interface IOrderItemService {

    List<OrderItemDTO> getOrderItemsById(long id);
}
