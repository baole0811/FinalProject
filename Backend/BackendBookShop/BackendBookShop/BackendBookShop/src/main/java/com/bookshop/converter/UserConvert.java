package com.bookshop.converter;

import com.bookshop.dto.UserDTO;
import com.bookshop.model.User;

public class UserConvert {
    public static UserDTO toModel(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setGender(user.getGender());
        userDTO.setAddress(user.getAddress());
        return userDTO;
    }
}
