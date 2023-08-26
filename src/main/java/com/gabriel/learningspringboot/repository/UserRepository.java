package com.gabriel.learningspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.gabriel.learningspringboot.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	
	UserDetails findByEmail(String email);
	
}
