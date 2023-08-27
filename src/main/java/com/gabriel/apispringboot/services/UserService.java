package com.gabriel.apispringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gabriel.apispringboot.entities.User;
import com.gabriel.apispringboot.repository.UserRepository;
import com.gabriel.apispringboot.services.exceptions.DatabaseException;
import com.gabriel.apispringboot.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	public User insert(User user)
	{
		return repository.save(user);
	}
	
	
	public void delete(Long id)
	{
		try {
			repository.deleteById(id);			
		}catch(EntityNotFoundException e)
		{
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e)
		{
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public UserDetails findByEmail(String email)
	{
		return repository.findByEmail(email);
	}
	
	public User update(Long id,User obj)
	{
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity,obj);
			return repository.save(entity);			
		}catch(EntityNotFoundException e)
		{
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
	
}
