package com.gabriel.apispringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.apispringboot.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
