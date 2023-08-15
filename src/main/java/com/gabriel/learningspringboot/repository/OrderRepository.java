package com.gabriel.learningspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.learningspringboot.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
