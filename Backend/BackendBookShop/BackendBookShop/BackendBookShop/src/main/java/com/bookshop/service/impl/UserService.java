package com.bookshop.service.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.bookshop.converter.UserConvert;
import com.bookshop.dto.*;
import com.bookshop.model.User;
import com.bookshop.repository.UserRepository;
import com.bookshop.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDTO getUserInfo(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setAddress(user.getAddress());
        userDTO.setGender(user.getGender());
        userDTO.setDateOfBirth(user.getDateOfBirth());

        return userDTO;
    }

    @Override
    public void changeUserInfo(User user, ChangeInfoDTO userDTO) {
        User userRecent = userRepository.findByEmail(user.getEmail()).get();

        userRecent.setFirstName(userDTO.getFirstName());
        userRecent.setLastName(userDTO.getLastName());
        userRecent.setAddress(userDTO.getAddress());
        userRecent.setGender(userDTO.getGender());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

       if(userDTO.getDateOfBirth() != null){
           try {
               Date date = format.parse(userDTO.getDateOfBirth());
               System.out.println(date);
               userRecent.setDateOfBirth(date);

           } catch (ParseException e) {
               e.printStackTrace();
           }
       }
        userRepository.save(userRecent);
    }

    @Override
    public ResponseMessage changeUserPassword(User user, ChangePassword changePassword) {
        ResponseMessage responseMessage = new ResponseMessage();
        User userRecent = userRepository.findByEmail(user.getEmail()).get();
        if (passwordEncoder.matches(changePassword.getOldPassword(), userRecent.getPassword())) {
            userRecent.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
            userRepository.save(userRecent);
            responseMessage.setResult("change password success");
            return responseMessage;
        } else {
            responseMessage.setResult("password is wrong");
        }
        return responseMessage;
    }

    @Override
    public ResponseMessage resetPassword(User user, ResetPassword resetPassword) {
        ResponseMessage responseMessage = new ResponseMessage();
        User userRecent = userRepository.findByEmail(user.getEmail()).get();
        userRecent.setPassword(passwordEncoder.encode(resetPassword.getNewPassword()));
        userRepository.save(userRecent);
        responseMessage.setResult("reset pass success");
        return responseMessage;
    }


    @Override
    public List<UserDTO> getAllAccount() {
        List<User> users = userRepository.findAll();
        List<UserDTO> results = new ArrayList<>();
        for (User user : users){
            results.add(UserConvert.toModel(user));
        }
        return results;
    }
}
