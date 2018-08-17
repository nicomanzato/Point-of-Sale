package com.nicolas.pos.test;

import java.util.List;
import java.util.Random;

import com.nicolas.pos.dao.DaoFactory;
import com.nicolas.pos.model.Order;
import com.nicolas.pos.model.Product;

public class GenerateOrder {

	
	public static Order GenerateRandomOrder() {
		
		List<Product> products = DaoFactory.getProductDao().getProducts();
		
		int random = new Random().nextInt(50);
		
		Order order = new Order();
		
		for (int i=0; i < random; i++) {
			
			Product randomProduct = products.get(new Random().nextInt(products.size()));
						
			order.addProduct( randomProduct );
		}
		
		return order;
		
	}
	
}
