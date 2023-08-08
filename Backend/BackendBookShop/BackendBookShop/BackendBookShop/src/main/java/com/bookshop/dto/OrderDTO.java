package com.bookshop.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends AbstractDTO<OrderDTO> {
    private String nameRecipient;
    private String address;
    private String phoneNumber;
    private String payment;
    private double totalMoney;
    private long userId;
    private long[] ids;
}
