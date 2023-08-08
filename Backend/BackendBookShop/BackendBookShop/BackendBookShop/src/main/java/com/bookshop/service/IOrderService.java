package com.bookshop.service;

import com.bookshop.dto.OrderDTO;
import com.bookshop.model.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IOrderService {
    OrderDTO createOrderBook(OrderDTO orderDTO, User user) throws MessagingException, UnsupportedEncodingException;

    List<OrderDTO> getAllOrders(User user);
}
