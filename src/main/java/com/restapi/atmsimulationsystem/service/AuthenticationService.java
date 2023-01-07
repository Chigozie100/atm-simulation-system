package com.restapi.atmsimulationsystem.service;

import com.restapi.atmsimulationsystem.model.User;
import com.restapi.atmsimulationsystem.payload.requests.AuthenticationRequest;
import com.restapi.atmsimulationsystem.payload.requests.RegisterRequest;
import com.restapi.atmsimulationsystem.payload.responses.AuthenticationResponse;
import org.apache.tomcat.websocket.AuthenticationException;

public interface AuthenticationService {
    void register(RegisterRequest request);

    String authenticate(AuthenticationRequest request) throws AuthenticationException;
}
