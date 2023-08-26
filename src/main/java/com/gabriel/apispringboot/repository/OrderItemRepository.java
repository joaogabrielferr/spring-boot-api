package com.gabriel.apispringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.apispringboot.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
