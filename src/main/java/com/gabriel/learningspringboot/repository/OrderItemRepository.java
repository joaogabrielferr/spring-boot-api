package com.gabriel.learningspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.learningspringboot.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
