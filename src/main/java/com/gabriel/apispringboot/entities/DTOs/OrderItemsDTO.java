package com.gabriel.apispringboot.entities.DTOs;

import jakarta.validation.constraints.NotNull;

public record OrderItemsDTO(
@NotNull(message = "product id can't be null")
Long productId,
Integer quantity
) {}