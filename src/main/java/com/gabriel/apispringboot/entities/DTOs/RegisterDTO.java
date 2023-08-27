package com.gabriel.apispringboot.entities.DTOs;

import com.gabriel.apispringboot.entities.enums.UserRole;

public record RegisterDTO(String email,String name,String phone,String password,UserRole role) {

}
