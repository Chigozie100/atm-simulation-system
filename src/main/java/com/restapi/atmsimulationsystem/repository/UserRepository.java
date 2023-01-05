package com.restapi.atmsimulationsystem.repository;

import com.restapi.atmsimulationsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
