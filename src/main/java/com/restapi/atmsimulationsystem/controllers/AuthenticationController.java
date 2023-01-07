package com.restapi.atmsimulationsystem.controllers;

import com.restapi.atmsimulationsystem.payload.requests.AuthenticationRequest;
import com.restapi.atmsimulationsystem.payload.requests.RegisterRequest;
import com.restapi.atmsimulationsystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
   private final AuthenticationService service;

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {
        service.register(request);
        return "user successfully registered";
    }
    @PostMapping("/authenticate")
    public String authenticate(
            @RequestBody AuthenticationRequest request) throws AuthenticationException {
       String response= service.authenticate(request);
       return "success" + response;
    }

}
