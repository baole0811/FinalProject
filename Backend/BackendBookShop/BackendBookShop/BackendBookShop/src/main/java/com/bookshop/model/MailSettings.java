package com.bookshop.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Configuration
public class MailSettings {
    @Value(value = "${spring.mail.username}")
    private String email;
}
