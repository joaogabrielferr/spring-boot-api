package com.gabriel.apispringboot.entities.DTOs;

public record UserResponseDTO(
		String name,
		String email,
		String phone
	) {

}
