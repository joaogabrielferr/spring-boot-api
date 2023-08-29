package com.gabriel.apispringboot.entities.DTOs;

import jakarta.validation.constraints.NotNull;

public record InsertCategoryDTO(@NotNull String name) {

}
