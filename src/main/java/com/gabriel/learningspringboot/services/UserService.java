package com.gabriel.learningspringboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.learningspringboot.entities.User;
import com.gabriel.learningspringboot.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll()
	{
		return repository.findAll();
	}
	
	public User findUser(Long id)
	{
		return repository.findById(id).get();
	}
	
	
}
