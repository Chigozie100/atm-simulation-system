package com.restapi.atmsimulationsystem.service;


import com.restapi.atmsimulationsystem.model.User;
import com.restapi.atmsimulationsystem.payload.requests.UserAmountRequestDto;
import com.restapi.atmsimulationsystem.payload.requests.UserRequestDto;
import com.restapi.atmsimulationsystem.payload.responses.UserResponseDto;

import java.math.BigDecimal;

public interface UserService {

    void deleteUser(Long id);

    User updateUser(Long id, UserRequestDto requestDto);

    void depositMoney(UserAmountRequestDto requestDto);

    void withdrawMoney(BigDecimal amount);

    UserResponseDto checkAccountBalance();


}
