package com.restapi.atmsimulationsystem.service.impl;

import com.restapi.atmsimulationsystem.exceptions.InsufficientFundException;
import com.restapi.atmsimulationsystem.exceptions.UserNotFoundException;
import com.restapi.atmsimulationsystem.model.User;
import com.restapi.atmsimulationsystem.payload.requests.UserAmountRequestDto;
import com.restapi.atmsimulationsystem.payload.requests.UserRequestDto;
import com.restapi.atmsimulationsystem.payload.responses.UserResponseDto;
import com.restapi.atmsimulationsystem.repository.UserRepository;
import com.restapi.atmsimulationsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, UserRequestDto requestDto) {
        User user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException("user not found"));
        user.setFullName(requestDto.getFullName());
        user.setEmail(requestDto.getEmail());
        user.setAccountNumber(requestDto.getAccountNumber());
        user.setPin(requestDto.getPin());
        user.setCardNo(requestDto.getCardNo());
        return userRepository.save(user);
    }

    @Override
    public void depositMoney(Long id, UserAmountRequestDto requestDto) {
        User user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException("user not found"));
        user.setAmount(requestDto.getAmount());
        userRepository.save(user);
    }

    @Override
    public void withdrawMoney(Long id, BigDecimal amount) {
        User user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException("user not found"));
        if(user.getAmount().compareTo(new BigDecimal(String.valueOf(amount))) >= 0){
            BigDecimal balance=user.getAmount().subtract(amount);
            user.setAmount(balance);
            userRepository.save(user);
        } else {
            throw new InsufficientFundException("insufficient fund");
        }
    }

    @Override
    public UserResponseDto checkAccountBalance(Long id) {
        User user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException("user not found"));
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setAmount(user.getAmount());
        return responseDto;
    }
}
