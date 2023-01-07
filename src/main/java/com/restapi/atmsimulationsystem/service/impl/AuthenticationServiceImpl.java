package com.restapi.atmsimulationsystem.service.impl;

import com.restapi.atmsimulationsystem.enums.Role;
import com.restapi.atmsimulationsystem.exceptions.UserNotFoundException;
import com.restapi.atmsimulationsystem.model.User;
import com.restapi.atmsimulationsystem.payload.requests.AuthenticationRequest;
import com.restapi.atmsimulationsystem.payload.requests.RegisterRequest;
import com.restapi.atmsimulationsystem.repository.UserRepository;
import com.restapi.atmsimulationsystem.security.JwtService;
import com.restapi.atmsimulationsystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(RegisterRequest request) {
        Optional<User> userExist =repository.findByEmail(request.getEmail());

        if (userExist.isPresent()){
            throw new UserNotFoundException("user already exist");
        }
        com.restapi.atmsimulationsystem.model.User user = new com.restapi.atmsimulationsystem.model.User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPin(passwordEncoder.encode(request.getPin()));
        user.setRole(Role.ADMIN);
        repository.save(user);
    }

   @Override
    public String authenticate(AuthenticationRequest request) throws AuthenticationException {
       Authentication auth=authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPin()));

       if(auth.isAuthenticated()){
           String jwt = "Bearer: " + JwtService.generateToken
                   (new org.springframework.security.core.userdetails.User(request.getEmail(), request.getPin(), new ArrayList<>()));
           return jwt;
       } else {
           throw new AuthenticationException("cardNo or pin Not Authenticated");
       }
    }

}
