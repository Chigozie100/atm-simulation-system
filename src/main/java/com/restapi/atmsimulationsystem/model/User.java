package com.restapi.atmsimulationsystem.model;

import com.restapi.atmsimulationsystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "d_user")
public class User{
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;

    private String email;
    private Integer accountNumber;
    private String pin;
    private String cardNo;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String fullName, String email, Integer accountNumber, String pin, String cardNo, Role role) {
        this.email = email;
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.cardNo = cardNo;
        this.role = role;
    }

    public User(String fullName, String pin, Role role) {
        this.fullName = fullName;
        this.pin = pin;
        this.role = role;
    }

}
