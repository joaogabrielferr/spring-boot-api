package com.gabriel.apispringboot.entities.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InsertProductDTO(
		
	@NotNull(message = "Product name can't be null")
	@NotBlank(message = "Product name can't be blank")
	String name,
	String description,
	Double price,
	String imgUrl,
	@NotNull(message = "Category Id can't be null")
	Long categoryId
		) {

}
