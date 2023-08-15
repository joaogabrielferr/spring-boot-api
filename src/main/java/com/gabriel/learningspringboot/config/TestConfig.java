package com.gabriel.learningspringboot.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gabriel.learningspringboot.entities.Order;
import com.gabriel.learningspringboot.entities.User;
import com.gabriel.learningspringboot.entities.enums.OrderStatus;
import com.gabriel.learningspringboot.repository.OrderRepository;
import com.gabriel.learningspringboot.repository.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void run(String... args) throws Exception {
		//overriding method from CommandLineRunner interface
		//this function runs right after applications starts
		
		User user1 = new User(null,"fulano","fulano@gmail.com","999999999","123");
		User user2 = new User(null,"beltrano","beltrano@gmail.com","999999999","123");
		
		Order o1 = new Order(null,Instant.now(),user1,OrderStatus.SHIPPED);
		Order o2 = new Order(null,Instant.now(),user2,OrderStatus.SHIPPED);
		
		userRepository.saveAll(Arrays.asList(user1,user2));
		orderRepository.saveAll(Arrays.asList(o1,o2));
		
	}
	
		
	
	
}
