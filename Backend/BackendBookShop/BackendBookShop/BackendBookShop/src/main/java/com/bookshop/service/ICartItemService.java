package com.bookshop.service;

import com.bookshop.dto.CartItemDTO;
import com.bookshop.model.User;

import java.util.List;

public interface ICartItemService {
    CartItemDTO saveCart(long id, int quantity, User user);

    List<CartItemDTO> getAllCarts(User user);

    void deleteCartItems(long[] ids);

    void deleteCartItem(long id);

    CartItemDTO updateCartItem(long id, CartItemDTO cartItemDTO,User user);
}
