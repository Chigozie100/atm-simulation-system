package com.restapi.atmsimulationsystem.payload.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserAmountRequestDto {
    private BigDecimal amount;

}
