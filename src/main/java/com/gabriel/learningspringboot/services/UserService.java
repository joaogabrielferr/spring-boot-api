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
	
	public User insert(User user)
	{
		return repository.save(user);
	}
	
	public void delete(Long id)
	{
		repository.deleteById(id);
	}
	
	public User update(Long id,User obj)
	{
		User entity = repository.getReferenceById(id);
		updateData(entity,obj);
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
	
}
