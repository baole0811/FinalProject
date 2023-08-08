package com.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderMail {
    private String emailUserCreated;
    private String nameReceiver;
    private String address;
    private String phoneNumber;
    private double totalMoney;
    private Date createDate;

}
