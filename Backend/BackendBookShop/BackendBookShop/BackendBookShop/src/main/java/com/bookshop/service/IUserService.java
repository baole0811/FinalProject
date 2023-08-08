package com.bookshop.service;

import com.bookshop.dto.*;
import com.bookshop.model.User;

import java.util.List;

public interface IUserService {
    UserDTO getUserInfo(User user);

    void changeUserInfo(User user, ChangeInfoDTO userDTO);

    ResponseMessage changeUserPassword(User user, ChangePassword changePassword);

    ResponseMessage resetPassword(User user, ResetPassword resetPassword);

    List<UserDTO> getAllAccount();
}
