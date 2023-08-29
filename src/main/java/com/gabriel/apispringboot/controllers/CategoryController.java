package com.gabriel.apispringboot.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.apispringboot.entities.Category;
import com.gabriel.apispringboot.entities.DTOs.InsertCategoryDTO;
import com.gabriel.apispringboot.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
			
		List<Category> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findCategory(@PathVariable Long id){
		
		Category Category = service.findCategory(id);
		
		return ResponseEntity.ok().body(Category);
		
	}
	
	@PostMapping
	public ResponseEntity<Category> createCategory(@Valid @RequestBody InsertCategoryDTO categoryDTO)
	{
		Category category = new Category(null,categoryDTO.name());
		
		category = service.insert(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
		return ResponseEntity.created(uri).body(category);
		
	}
	
}
