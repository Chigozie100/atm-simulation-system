package com.restapi.atmsimulationsystem.utils;

import com.restapi.atmsimulationsystem.enums.Role;
import com.restapi.atmsimulationsystem.model.User;

import java.math.BigDecimal;

public class MockUserUtils {
    public static User getMockUser(String username) {
        User user = new User();
        user.setFullName("smith");
        user.setEmail("smith@gmail.com");
        user.setAccountNumber(8673);
        user.setPin("863");
        user.setCardNo("8773");
        user.setAmount(BigDecimal.valueOf(600.00));
        user.setRole(Role.ADMIN);
        return user;
    }
}
