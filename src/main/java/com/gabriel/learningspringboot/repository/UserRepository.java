package com.gabriel.learningspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.learningspringboot.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
