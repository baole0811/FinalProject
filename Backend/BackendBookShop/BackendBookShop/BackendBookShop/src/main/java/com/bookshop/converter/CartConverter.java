package com.bookshop.converter;

import com.bookshop.dto.CartItemDTO;
import com.bookshop.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartConverter {
    public static CartItemDTO toModel(CartItem cartItemEntity){
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setQuantity(cartItemEntity.getQuantity());
        cartItemDTO.setPrice(cartItemEntity.getPriceItem());
        cartItemDTO.setProduct_id(cartItemEntity.getBook().getId());
        cartItemDTO.setId(cartItemEntity.getId());
        cartItemDTO.setCreateBy(cartItemEntity.getCreateBy());
        cartItemDTO.setCreateDate(cartItemEntity.getCreateDate());
        cartItemDTO.setModifiedBy(cartItemEntity.getModifiedBy());
        cartItemDTO.setModifiedDate(cartItemEntity.getModifiedDate());
        cartItemDTO.setUser_id(cartItemEntity.getUser().getId());
        cartItemDTO.setNameBook(cartItemEntity.getBook().getName());
        cartItemDTO.setImageURL(cartItemEntity.getBook().getImageURL());
        return cartItemDTO;
    }

}
