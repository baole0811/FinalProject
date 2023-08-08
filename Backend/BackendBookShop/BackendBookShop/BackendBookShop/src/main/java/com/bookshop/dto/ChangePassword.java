package com.bookshop.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChangePassword {
    private String oldPassword;
    private String newPassword;
}
