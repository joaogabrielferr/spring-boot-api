package com.gabriel.apispringboot.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.apispringboot.entities.User;
import com.gabriel.apispringboot.entities.DTOs.AuthenticationDTO;
import com.gabriel.apispringboot.entities.DTOs.LoginResponseDTO;
import com.gabriel.apispringboot.entities.DTOs.RegisterDTO;
import com.gabriel.apispringboot.infra.security.TokenService;
import com.gabriel.apispringboot.services.UserService;

import jakarta.validation.Valid;

@RestController
@Validated
public class AuthenticationController {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody AuthenticationDTO data)
	{
		UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		Authentication auth = this.authenticationManager.authenticate(usernamePassword);
	
		String token = tokenService.generateToken((User)auth.getPrincipal());
		
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<Void> register(@Valid @RequestBody RegisterDTO data)
	{
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		
		User user = new User(null,data.name(),data.email(),data.phone(),encryptedPassword,data.role());
		
		User _user =  userService.insert(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(_user.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}
	
	
}
