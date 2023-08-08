package com.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeInfoDTO {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String address;
}
