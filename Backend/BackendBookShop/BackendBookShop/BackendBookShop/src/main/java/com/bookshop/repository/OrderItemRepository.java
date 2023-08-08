package com.bookshop.repository;

import com.bookshop.model.Order;
import com.bookshop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findOrderItemsByOrder(Order order);

    List<OrderItem> findOrderItemsByOrderOrderById(long id);
}
