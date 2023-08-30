package com.gabriel.apispringboot.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.apispringboot.entities.Order;
import com.gabriel.apispringboot.entities.OrderItem;
import com.gabriel.apispringboot.entities.Product;
import com.gabriel.apispringboot.entities.User;
import com.gabriel.apispringboot.entities.DTOs.InsertOrderDTO;
import com.gabriel.apispringboot.entities.DTOs.OrderItemsDTO;
import com.gabriel.apispringboot.repository.OrderRepository;
import com.gabriel.apispringboot.repository.ProductRepository;
import com.gabriel.apispringboot.repository.UserRepository;
import com.gabriel.apispringboot.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Order> findAll()
	{
		return repository.findAll();
	}
	
	public Order findOrder(Long id)
	{
		Optional<Order> order = repository.findById(id);
		return order.orElseThrow(()-> new ResourceNotFoundException("Order",id));
	}
	
	public Order insertOrder(InsertOrderDTO insertOrderDTO)
	{
		User user = userRepository.findById(insertOrderDTO.clientId()).orElseThrow(()-> new ResourceNotFoundException("User", insertOrderDTO.clientId()));
		
		Instant moment = null;
		if(insertOrderDTO.moment() != null)
		{
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			LocalDate localDate = LocalDate.parse(insertOrderDTO.moment(), dateFormatter);
			
			moment = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
			
		}
		
		Order order = new Order(null,moment,user,insertOrderDTO.status());
			
		Map<Long,Product> productSet = new HashMap<>();
		
		for(OrderItemsDTO orderItemDTO : insertOrderDTO.items())
		{
			Product product = null;
			if(productSet.get(orderItemDTO.productId()) != null)
			{
				product = productSet.get(orderItemDTO.productId());
			}else
			{
				product = productRepository.findById(orderItemDTO.productId()).orElseThrow(()-> new ResourceNotFoundException("Product", orderItemDTO.productId()));
				productSet.put(product.getId(), product);
			}
			//Double price = orderItemDTO.quantity() * product.getPrice();
			OrderItem orderItem = new OrderItem(order,product,orderItemDTO.quantity(),product.getPrice());
			System.out.println("order item created with quantity " + orderItem.getQuantity() + " and price " + orderItem.getPrice());
			order.getItems().add(orderItem);
		}
		
		order = repository.save(order);
		
		return order;
		
	}
	

	
	
}
