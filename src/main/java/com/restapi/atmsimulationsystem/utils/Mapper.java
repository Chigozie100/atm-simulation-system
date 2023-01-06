package com.restapi.atmsimulationsystem.utils;

import com.restapi.atmsimulationsystem.model.User;
import com.restapi.atmsimulationsystem.payload.requests.UserRequestDto;
import com.restapi.atmsimulationsystem.payload.responses.UserResponseDto;

public class Mapper {
    public static User mapToEntity(UserRequestDto requestDto){
        User user = new User();
        user.setFullName(requestDto.getFullName());
        user.setAccountNumber(requestDto.getAccountNumber());
        user.setPin(requestDto.getPin());
        user.setCardNo(requestDto.getCardNo());
        return user;
    }

}
