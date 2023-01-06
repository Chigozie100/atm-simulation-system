package com.restapi.atmsimulationsystem.model;

import com.restapi.atmsimulationsystem.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "d_user")
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return pin;
    }

    @Override
    public String getUsername() {
        return fullName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
