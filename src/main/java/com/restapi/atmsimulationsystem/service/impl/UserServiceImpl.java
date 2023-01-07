package com.restapi.atmsimulationsystem.service.impl;

import com.restapi.atmsimulationsystem.exceptions.InsufficientFundException;
import com.restapi.atmsimulationsystem.exceptions.UserNotFoundException;
import com.restapi.atmsimulationsystem.model.User;
import com.restapi.atmsimulationsystem.payload.requests.UserAmountRequestDto;
import com.restapi.atmsimulationsystem.payload.requests.UserRequestDto;
import com.restapi.atmsimulationsystem.payload.responses.UserResponseDto;
import com.restapi.atmsimulationsystem.repository.UserRepository;
import com.restapi.atmsimulationsystem.service.UserService;
import com.restapi.atmsimulationsystem.utils.UserDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return null;
    }

    @Override
    public User updateUser(Long id, UserRequestDto requestDto) {
        User user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException("user not found"));
        user.setFullName(requestDto.getFullName());
        user.setAccountNumber(requestDto.getAccountNumber());
        user.setPin(requestDto.getPin());
        user.setCardNo(requestDto.getCardNo());
        return userRepository.save(user);
    }

    @Override
    public void depositMoney(UserAmountRequestDto requestDto) {
        String email=UserDetails.getLoggedInUserDetails();
        User user=userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("email does not exist"));
        user.setAmount(requestDto.getAmount());
        userRepository.save(user);
    }

    @Override
    public void withdrawMoney(BigDecimal amount) {
        String email=UserDetails.getLoggedInUserDetails();
        User user=userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("email does not exist"));
        if(user.getAmount().compareTo(new BigDecimal(String.valueOf(amount))) >= 0){
            BigDecimal balance=user.getAmount().subtract(amount);
            user.setAmount(balance);
            userRepository.save(user);
        } else {
            throw new InsufficientFundException("insufficient fund");
        }
    }

    @Override
    public UserResponseDto checkAccountBalance() {
        String email=UserDetails.getLoggedInUserDetails();
        User user=userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("email does not exist"));
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setAmount(user.getAmount());
        return responseDto;
    }
}
