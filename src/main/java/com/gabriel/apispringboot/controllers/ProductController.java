package com.gabriel.apispringboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.apispringboot.entities.Product;
import com.gabriel.apispringboot.services.ProductService;

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
	
}
