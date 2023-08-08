package com.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO extends AbstractDTO<BookDTO>{
    private String name;
    private String author;
    private String description;
    private String codeCategory;
    private String nameCategory;
    private double price;
    private String urlImage;
    private int quantity;
    private int sold;
}
