package com.bookshop.service;

import com.bookshop.dto.OrderMail;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface IMailService {
    void sendMailOrderInfo(OrderMail orderMail) throws MessagingException, UnsupportedEncodingException;
}
