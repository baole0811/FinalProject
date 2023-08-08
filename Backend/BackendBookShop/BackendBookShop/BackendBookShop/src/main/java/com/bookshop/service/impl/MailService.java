package com.bookshop.service.impl;

import com.bookshop.dto.OrderMail;
import com.bookshop.model.MailSettings;
import com.bookshop.service.IMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
@RequiredArgsConstructor
public class MailService implements IMailService {
    private final MailSettings mailSettings;
    private final JavaMailSender javaMailSender;
    @Override
    public void sendMailOrderInfo(OrderMail orderMail) throws MessagingException, UnsupportedEncodingException {
        String toAddress = orderMail.getEmailUserCreated();
        String fromAddress = mailSettings.getEmail();
        String subject = "This email is a notification email, you have just placed a new order";
        String content = "Hello [[name]],<br>"
                + "you just got 1 order <br>"
                + "Here is the basic information of the order: <br>"
                + "Address : [[address]] <br>"
                + "Phone Number: [[phone]] <br>"
                + "Create order date: [[createDate]] <br>"
                + "Total money of the order: [[totalMoney]] <br>";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", orderMail.getNameReceiver());
        content = content.replace("[[address]]", orderMail.getAddress());
        content = content.replace("[[phone]]", orderMail.getPhoneNumber());
        content = content.replace("[[createDate]]", String.valueOf(orderMail.getCreateDate()));
        content = content.replace("[[totalMoney]]", String.valueOf(orderMail.getTotalMoney()));

        helper.setText(content, true);

        javaMailSender.send(message);
    }
    public void sendMailCodeVerify(String email, String code) throws MessagingException {
        String toAddress = email;
        String fromAddress = mailSettings.getEmail();
        String subject = "this email to send the confirmation code";
        String content = "this is your code: <br>"
                + "[[code]] .<br>"
                + "Please do not share this code <br>"
                + "this code will expire after 24h <br>";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[code]]", code);


        helper.setText(content, true);

        javaMailSender.send(message);
    }
}
