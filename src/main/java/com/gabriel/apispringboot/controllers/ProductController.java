package com.gabriel.apispringboot.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.apispringboot.entities.Product;
import com.gabriel.apispringboot.entities.DTOs.InsertProductDTO;
import com.gabriel.apispringboot.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
			
		List<Product> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findProduct(@PathVariable Long id){
		
		Product Product = service.findProduct(id);
		
		return ResponseEntity.ok().body(Product);
		
	}
	
	@PostMapping
	public ResponseEntity<Void> insertProduct(@Valid @RequestBody InsertProductDTO productDTO)
	{
		Product product = new Product(null,productDTO.name(),productDTO.description(),productDTO.price(),productDTO.imgUrl());
			
		product = service.insert(product,productDTO.categoryId());
		
		return ResponseEntity.ok().build();
		
	}
	
}
