package com.restapi.atmsimulationsystem.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String fullName;
    private String email;
    private Integer accountNumber;
    private String pin;
    private String cardNo;
}
