package com.restapi.atmsimulationsystem.model;

import com.restapi.atmsimulationsystem.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "d_user")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private Integer accountNumber;
    private Integer pin;
    private Integer cardNo;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String fullName, Integer accountNumber, Integer pin, Integer cardNo, BigDecimal amount, Role role) {
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.cardNo = cardNo;
        this.amount = amount;
        this.role = role;
    }

    public User(String fullName, Integer accountNumber, Integer pin, Integer cardNo, Role role) {
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.cardNo = cardNo;
        this.role = role;
    }
}
