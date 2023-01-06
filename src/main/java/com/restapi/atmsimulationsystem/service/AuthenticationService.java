package com.restapi.atmsimulationsystem.service;

import com.restapi.atmsimulationsystem.payload.requests.AuthenticationRequest;
import com.restapi.atmsimulationsystem.payload.requests.RegisterRequest;
import com.restapi.atmsimulationsystem.payload.responses.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
