package com.kais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kais.dao.UserRepository;
import com.kais.entities.User;

@SpringBootApplication
public class SecurityDemoApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder myEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SecurityDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("user", myEncoder.encode("user"), "ROLE_USER", true));
		userRepository.save(new User("admin", myEncoder.encode("admin"), "ROLE_USER, ROLE_ADMIN", true));
	}

}
