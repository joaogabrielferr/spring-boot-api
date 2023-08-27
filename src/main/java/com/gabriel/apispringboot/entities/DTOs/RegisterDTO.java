package com.gabriel.apispringboot.entities.DTOs;



import com.gabriel.apispringboot.entities.enums.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
		@NotNull(message = "email is required") String email,
		String name,
		String phone,
		@NotNull(message = "password is required")
		@NotBlank(message = "password can not be blank")
		String password,
		UserRole role) {

}
