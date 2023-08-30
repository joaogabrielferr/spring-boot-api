package com.gabriel.apispringboot.entities.DTOs;

public record LoginResponseDTO(
		String token,
		UserResponseDTO user
		) {
}
