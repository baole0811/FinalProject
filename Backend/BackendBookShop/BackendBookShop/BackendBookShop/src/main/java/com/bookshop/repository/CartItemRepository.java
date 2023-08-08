package com.bookshop.repository;

import com.bookshop.model.CartItem;
import com.bookshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    List <CartItem> findCartItemByUser(User user);
}
