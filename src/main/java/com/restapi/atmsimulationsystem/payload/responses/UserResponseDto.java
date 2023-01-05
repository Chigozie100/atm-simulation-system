package com.restapi.atmsimulationsystem.payload.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String fullName;
    private Integer accountNumber;
    private Integer pin;
    private Integer cardNo;
    private BigDecimal amount;
}
