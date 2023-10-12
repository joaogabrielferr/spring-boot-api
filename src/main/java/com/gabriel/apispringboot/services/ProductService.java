package com.gabriel.apispringboot.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.apispringboot.entities.Category;
import com.gabriel.apispringboot.entities.Product;
import com.gabriel.apispringboot.repository.CategoryRepository;
import com.gabriel.apispringboot.repository.ProductRepository;
import com.gabriel.apispringboot.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Product> findAll()
	{
		return repository.findAll();
	}
	
	public Product findProduct(Long id)
	{
		Optional<Product> product = repository.findById(id);
		return product.orElseThrow(()-> new ResourceNotFoundException("Product",id));
	}
	
	public Product insert(Product product,Long categoryId)
	{
		
		Optional<Category> categoryOp = categoryRepository.findById(categoryId);
		Category category = categoryOp.orElseThrow(()-> new ResourceNotFoundException("Category",categoryId));
		product.getCategories().add(category);
		return repository.save(product);
	}
}
