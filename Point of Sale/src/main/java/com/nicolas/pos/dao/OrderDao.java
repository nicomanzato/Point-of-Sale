package com.nicolas.pos.dao;

import java.util.List;
import java.util.Observer;

import com.nicolas.pos.model.Order;

public interface OrderDao{

	public void save(Order order);
	
	public void update(Order order);
	
	public void delete(Order order);
		
	public List<Order> getOrders();
	
	public Order getOrder(Long idOrder);
	
	public void addObserver(Observer observer);
	
}