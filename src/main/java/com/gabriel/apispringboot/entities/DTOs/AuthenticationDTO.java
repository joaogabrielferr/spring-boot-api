package com.gabriel.apispringboot.entities.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(
		@NotNull(message = "email is required")
		String email,
		@NotNull(message = "password is required")
		@NotBlank(message = "password can not be blank")
		String password
		) {

}
