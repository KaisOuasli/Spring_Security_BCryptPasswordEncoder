package com.kais.dao   ;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kais.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByUsername(String name);
}
