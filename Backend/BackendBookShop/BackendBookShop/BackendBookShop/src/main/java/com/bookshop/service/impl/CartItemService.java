package com.bookshop.service.impl;


import com.bookshop.converter.CartConverter;
import com.bookshop.dto.CartItemDTO;
import com.bookshop.model.Book;
import com.bookshop.model.CartItem;
import com.bookshop.model.User;
import com.bookshop.repository.BookRepository;
import com.bookshop.repository.CartItemRepository;
import com.bookshop.service.ICartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {
    private final BookRepository bookRepository;
    private final CartItemRepository cartItemRepository;
    @Override
    public CartItemDTO saveCart(long id, int quantity , User user) {
        Book book = bookRepository.findById(id).get();
        CartItem cartItem = new CartItem();
        cartItem.setPriceItem(book.getPrice());
        cartItem.setQuantity(quantity);
        cartItem.setCreateDate(new Date(System.currentTimeMillis()));
        cartItem.setCreateBy(user.getFirstName());
        cartItem.setUser(user);
        cartItem.setBook(book);

        return CartConverter.toModel(cartItemRepository.save(cartItem));
    }

    @Override
    public List<CartItemDTO> getAllCarts(User user) {
        List<CartItem>  cartItems = cartItemRepository.findCartItemByUser(user);
        List<CartItemDTO> result = new ArrayList<>();
        for (CartItem cartItem : cartItems){
            result.add(CartConverter.toModel(cartItem));
        }

        return result;
    }

    @Override
    public void deleteCartItems(long[] ids) {
        for(long id : ids){
            cartItemRepository.deleteById(id);
        }

    }

    @Override
    public void deleteCartItem(long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public CartItemDTO updateCartItem(long id, CartItemDTO cartItemDTO,User user) {
        CartItem cartItem = cartItemRepository.findById(id).get();
        cartItem.setModifiedBy(user.getFirstName());
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setModifiedDate(new Date(System.currentTimeMillis()));
        return CartConverter.toModel(cartItemRepository.save(cartItem));
    }
}
