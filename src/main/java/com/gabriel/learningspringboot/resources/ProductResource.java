package com.gabriel.learningspringboot.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.learningspringboot.entities.Product;
import com.gabriel.learningspringboot.services.ProductService;

@RestController
@RequestMapping(value="/products")
public class ProductResource {
	
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
