package com.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO extends AbstractDTO<OrderItemDTO>{
    private long order_id;
    private long product_id;
    private int quantity;
    private double price;
    private String nameBook;

}
