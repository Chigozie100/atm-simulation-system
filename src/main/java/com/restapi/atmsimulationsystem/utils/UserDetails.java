package com.restapi.atmsimulationsystem.utils;

import com.restapi.atmsimulationsystem.exceptions.UserNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserDetails {
    public static String getLoggedInUserDetails(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof org.springframework.security.core.userdetails.UserDetails)) {
            throw new UserNotFoundException("user not found");
        }
        return ((org.springframework.security.core.userdetails.UserDetails)principal).getUsername();
    }
}
