package com.restapi.atmsimulationsystem;

import com.restapi.atmsimulationsystem.enums.Role;
import com.restapi.atmsimulationsystem.model.User;
import com.restapi.atmsimulationsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@AllArgsConstructor
@Component
@SpringBootApplication
public class AtmSimulationSystemApplication implements CommandLineRunner {
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AtmSimulationSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("john", 5886, 8067, 56, Role.USER));

		userRepository.save(new User("doe", 45543, 908, 80, Role.USER));
	}
}
