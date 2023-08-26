package com.gabriel.apispringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.apispringboot.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
