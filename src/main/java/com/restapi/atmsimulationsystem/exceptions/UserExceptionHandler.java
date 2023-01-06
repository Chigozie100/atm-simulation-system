package com.restapi.atmsimulationsystem.exceptions;

import com.restapi.atmsimulationsystem.payload.responses.APIResponse;
import com.restapi.atmsimulationsystem.utils.Responder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<APIResponse> droneNotFound(UserNotFoundException ex){
        return Responder.notFound(ex.getMessage());
    }

    @ExceptionHandler(InsufficientFundException.class)
    public ResponseEntity<APIResponse> droneOverloaded(InsufficientFundException ex){
        return Responder.notFound(ex.getMessage());
    }
}

