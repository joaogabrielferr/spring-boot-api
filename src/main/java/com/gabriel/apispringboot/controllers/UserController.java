package com.gabriel.apispringboot.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.apispringboot.entities.User;
import com.gabriel.apispringboot.entities.DTOs.UserResponseDTO;
import com.gabriel.apispringboot.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
			
		List<User> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> findUser(@PathVariable Long id){
		
		User user = service.findById(id);
				
		return ResponseEntity.ok().body(new UserResponseDTO(user.getName(),user.getEmail(),user.getPhone()));
		
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj)
	{	
		User user = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id)
	{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id,@RequestBody User obj)
	{
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
}
