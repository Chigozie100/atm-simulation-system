package com.restapi.atmsimulationsystem;

import com.restapi.atmsimulationsystem.enums.Role;
import com.restapi.atmsimulationsystem.model.User;
import com.restapi.atmsimulationsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@AllArgsConstructor
@Component
@SpringBootApplication
public class AtmSimulationSystemApplication implements CommandLineRunner {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AtmSimulationSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(!(userRepository.findAll().size() > 0)) {
			userRepository.save(new User("john", "john@gamil.com", 5886,
					passwordEncoder.encode("87"), passwordEncoder.encode("565"), Role.USER));
			userRepository.save(new User("doe", "doe@gmail.com", 45543,
					passwordEncoder.encode("87"), passwordEncoder.encode("876"), Role.USER));

			userRepository.save(new User("smith", "smith@gmail.com", 87652,
					passwordEncoder.encode("90"), passwordEncoder.encode("76"), Role.USER));
		}
	}
}
