package com.gabriel.learningspringboot.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gabriel.learningspringboot.entities.Category;
import com.gabriel.learningspringboot.entities.Order;
import com.gabriel.learningspringboot.entities.OrderItem;
import com.gabriel.learningspringboot.entities.Payment;
import com.gabriel.learningspringboot.entities.Product;
import com.gabriel.learningspringboot.entities.User;
import com.gabriel.learningspringboot.entities.enums.OrderStatus;
import com.gabriel.learningspringboot.repository.CategoryRepository;
import com.gabriel.learningspringboot.repository.OrderItemRepository;
import com.gabriel.learningspringboot.repository.OrderRepository;
import com.gabriel.learningspringboot.repository.ProductRepository;
import com.gabriel.learningspringboot.repository.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {
		//overriding method from CommandLineRunner interface
		//this function runs right after applications starts
		

		Category c1 = new Category(null,"Electronics");
		Category c2 = new Category(null,"Books");
		
		Product p1 = new Product(null,"The Lord of The Rings","bla bla bla",50.0,"");
		Product p2 = new Product(null,"1984","bla bla bla",45.0,"");
		Product p3 = new Product(null,"LapTop","bla bla bla",1000.0,"");
		
		categoryRepository.saveAll(Arrays.asList(c1,c2));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		p1.getCategories().add(c1);
		p2.getCategories().add(c2);
		p3.getCategories().add(c2);
		p3.getCategories().add(c1);
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		User user1 = new User(null,"fulano","fulano@gmail.com","999999999","123");
		User user2 = new User(null,"beltrano","beltrano@gmail.com","999999999","123");
		
		Order o1 = new Order(null,Instant.now(),user1,OrderStatus.PAID);
		Order o2 = new Order(null,Instant.now(),user2,OrderStatus.SHIPPED);
		

		
		userRepository.saveAll(Arrays.asList(user1,user2));
		orderRepository.saveAll(Arrays.asList(o1,o2));
		
		OrderItem oi1 = new OrderItem(o1,p1,2,1000.0);
		OrderItem oi2 = new OrderItem(o1,p3,1,8000.0);
		OrderItem oi3 = new OrderItem(o2,p2,5,25000.0);
		
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3));
		
		Payment payment1 = new Payment(null,Instant.now(),o1);
		
		o1.setPayment(payment1);
		
		orderRepository.save(o1);

	}
	
		
	
	
}
