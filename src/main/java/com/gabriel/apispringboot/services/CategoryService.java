package com.gabriel.apispringboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.apispringboot.entities.Category;
import com.gabriel.apispringboot.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll()
	{
		return repository.findAll();
	}
	
	public Category findCategory(Long id)
	{
		return repository.findById(id).get();
	}
	

	
	
}
