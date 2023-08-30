package com.gabriel.apispringboot.entities.DTOs;

import java.util.List;

import com.gabriel.apispringboot.entities.enums.OrderStatus;


import jakarta.validation.constraints.NotNull;

public record InsertOrderDTO(
	@NotNull(message = "client id can't be null")
	Long clientId,
	String moment,
	OrderStatus status,
	@NotNull(message = "order items list can't be null")
	List<OrderItemsDTO>items
) {

}
