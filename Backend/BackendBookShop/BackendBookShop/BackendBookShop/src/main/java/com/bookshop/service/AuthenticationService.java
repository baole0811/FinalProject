package com.bookshop.service;

import com.bookshop.dto.AuthenticateReq;
import com.bookshop.dto.AuthenticatedResp;
import com.bookshop.dto.RegisterReq;
import com.bookshop.dto.ResponseMessage;
import com.bookshop.model.Role;
import com.bookshop.model.User;
import com.bookshop.repository.UserRepository;
import com.bookshop.service.impl.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;
    public AuthenticatedResp register(RegisterReq registerReq) {
        var user = User.builder()
                .firstName(registerReq.getFirstName())
                .lastName(registerReq.getLastName())
                .email(registerReq.getEmail())
                .password(passwordEncoder.encode(registerReq.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticatedResp.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticatedResp authenticate(AuthenticateReq authenticateReq) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticateReq.getEmail(),
                authenticateReq.getPassword()));

        var user = userRepository.findByEmail(authenticateReq.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatedResp.builder()
                .token(jwtToken)
                .build();
    }
    public ResponseMessage forgotPass(String email) throws MessagingException {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            User user = userRepository.findByEmail(email).get();
            String jwtToken = jwtService.generateToken(user);
            responseMessage.setResult("please check your email");
            mailService.sendMailCodeVerify(email,jwtToken);
            return responseMessage;
        }catch (NoSuchElementException e){
            responseMessage.setResult("your email does not exist");
            return responseMessage;
        }

    }
}
