package com.gabriel.learningspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.learningspringboot.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
