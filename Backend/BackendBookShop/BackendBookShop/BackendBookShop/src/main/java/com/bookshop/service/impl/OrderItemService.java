package com.bookshop.service.impl;

import com.bookshop.converter.OrderItemConverter;
import com.bookshop.dto.OrderItemDTO;
import com.bookshop.model.Order;
import com.bookshop.model.OrderItem;
import com.bookshop.repository.OrderItemRepository;
import com.bookshop.repository.OrderRepository;
import com.bookshop.service.IOrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService implements IOrderItemService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    @Override
    public List<OrderItemDTO> getOrderItemsById(long id) {
        Order order = orderRepository.findById(id).get();
        List<OrderItem> orderItems = orderItemRepository.findOrderItemsByOrder(order);
        List<OrderItemDTO> result = new ArrayList<>();
        for(OrderItem orderItem : orderItems){
            result.add(OrderItemConverter.toModel(orderItem));
        }

        return result;
    }
}
