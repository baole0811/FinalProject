package com.bookshop.controller;

import com.bookshop.dto.*;
import com.bookshop.service.AuthenticationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:3000/"})
@RequiredArgsConstructor
public class AuthenticatedController {
    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<AuthenticatedResp> register(@RequestBody RegisterReq registerReq){

        return ResponseEntity.ok(service.register(registerReq));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticatedResp> authenticate(@RequestBody AuthenticateReq authenticateReq){

        return ResponseEntity.ok(service.authenticate(authenticateReq));
    }
    @PostMapping(value = "/account")
    public ResponseMessage getCode(@RequestBody GetCode getCode) throws MessagingException {

        return service.forgotPass(getCode.getEmail());
    }
}
