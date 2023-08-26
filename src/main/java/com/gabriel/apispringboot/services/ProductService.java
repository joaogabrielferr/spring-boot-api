package com.gabriel.apispringboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.apispringboot.entities.Product;
import com.gabriel.apispringboot.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll()
	{
		return repository.findAll();
	}
	
	public Product findProduct(Long id)
	{
		return repository.findById(id).get();
	}
	

	
	
}
