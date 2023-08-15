package com.gabriel.learningspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.learningspringboot.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
