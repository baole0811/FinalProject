package com.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartItemDTO extends AbstractDTO<CartItemDTO> {
    private long user_id;
    private long product_id;
    private int quantity;
    private double price;
    private String nameBook;
    private String imageURL;
}
