package com.restapi.atmsimulationsystem.service.impl;

import com.restapi.atmsimulationsystem.enums.Role;
import com.restapi.atmsimulationsystem.exceptions.UserNotFoundException;
import com.restapi.atmsimulationsystem.model.User;
import com.restapi.atmsimulationsystem.payload.requests.AuthenticationRequest;
import com.restapi.atmsimulationsystem.payload.requests.RegisterRequest;
import com.restapi.atmsimulationsystem.payload.responses.AuthenticationResponse;
import com.restapi.atmsimulationsystem.repository.UserRepository;
import com.restapi.atmsimulationsystem.security.JwtService;
import com.restapi.atmsimulationsystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
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
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getFullName()));
        var user = repository.findByEmail(request.getEmail()).orElseThrow(()-> new UsernameNotFoundException("user not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
