package com.restapi.atmsimulationsystem.repository;

import com.restapi.atmsimulationsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    //Optional<User> findUserByEmail(String email);
}
