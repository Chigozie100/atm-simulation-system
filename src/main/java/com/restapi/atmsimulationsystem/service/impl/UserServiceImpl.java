package com.restapi.atmsimulationsystem.service.impl;

import com.restapi.atmsimulationsystem.enums.Role;
import com.restapi.atmsimulationsystem.exceptions.UserNotFoundException;
import com.restapi.atmsimulationsystem.model.User;
import com.restapi.atmsimulationsystem.payload.requests.UserRequestDto;
import com.restapi.atmsimulationsystem.repository.UserRepository;
import com.restapi.atmsimulationsystem.service.UserService;
import com.restapi.atmsimulationsystem.utils.Mapper;
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
        user.setAccountNumber(requestDto.getAccountNumber());
        user.setPin(requestDto.getPin());
        user.setCardNo(requestDto.getCardNo());
        return userRepository.save(user);
    }

    @Override
    public void depositMoney(Long id, Integer amount) {
        User user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException("user not found"));
        user.setAmount(BigDecimal.valueOf(amount));
    }
}
