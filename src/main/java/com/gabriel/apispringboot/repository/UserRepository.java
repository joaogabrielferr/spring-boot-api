package com.gabriel.apispringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.gabriel.apispringboot.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	
	User findByEmail(String email);
	
}
