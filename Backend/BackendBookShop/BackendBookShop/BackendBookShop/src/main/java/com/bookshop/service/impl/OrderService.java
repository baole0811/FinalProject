package com.bookshop.service.impl;

import com.bookshop.converter.OrderConverter;
import com.bookshop.converter.OrderItemConverter;
import com.bookshop.dto.OrderDTO;
import com.bookshop.dto.OrderMail;
import com.bookshop.model.*;
import com.bookshop.repository.BookRepository;
import com.bookshop.repository.CartItemRepository;
import com.bookshop.repository.OrderRepository;
import com.bookshop.service.IOrderService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final MailService mailService;
    private final BookRepository bookRepository;
    @Override
    public OrderDTO createOrderBook(OrderDTO orderDTO, User user) throws MessagingException, UnsupportedEncodingException {
        List<OrderItem> orderItems = new ArrayList<>();
        Order order = new Order();
        double totalMoney = 0;
        for (long id : orderDTO.getIds()){
            orderItems.add(OrderItemConverter.toEntity(cartItemRepository.findById(id).get()));
        }
        for(OrderItem orderItem : orderItems){
            orderItem.setOrder(order);
            totalMoney += (orderItem.getPriceItem() * orderItem.getQuantity());
            Book book = bookRepository.findById(orderItem.getBook().getId()).get();
            book.setSold(book.getSold() + orderItem.getQuantity());
            bookRepository.save(book);
        }
        for(long id :orderDTO.getIds()){
            cartItemRepository.deleteById(id);
        }

        order.setOrderItems(orderItems);
        order.setNameRecipient(orderDTO.getNameRecipient());
        order.setPhoneNumber(orderDTO.getPhoneNumber());
        order.setAddress(orderDTO.getAddress());
        order.setPayment(orderDTO.getPayment());
        order.setTotalPrice(totalMoney);
        order.setCreateBy(user.getFirstName());
        order.setCreateDate(new Date(System.currentTimeMillis()));
        order.setUser(user);

        OrderMail orderMail = new OrderMail(user.getEmail(), orderDTO.getNameRecipient(), orderDTO.getAddress(),
                orderDTO.getPhoneNumber(), totalMoney, new Date(System.currentTimeMillis()));
        mailService.sendMailOrderInfo(orderMail);
        return OrderConverter.toModel(orderRepository.save(order));
    }

    @Override
    public List<OrderDTO> getAllOrders(User user) {
        List<Order> orders = orderRepository.findOrdersByUser(user);
        List<OrderDTO> result = new ArrayList<>();
        for (Order order: orders){
            result.add(OrderConverter.toModel(order));
        }
        return result;
    }
}
