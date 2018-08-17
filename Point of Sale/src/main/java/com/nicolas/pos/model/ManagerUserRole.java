package com.nicolas.pos.model;

import com.nicolas.pos.dao.DaoFactory;
import javax.persistence.*;

@Entity  
@DiscriminatorValue("Manager")
public class ManagerUserRole extends UserRole{

	@Override
	public boolean createProduct(Product product, User user) {
		
		DaoFactory.getProductDao().save(product);
		return true;
	}

	@Override
	public boolean deleteProduct(Product product, User user) {
		
		DaoFactory.getProductDao().delete(product);
		return false;
	}

	@Override
	public boolean updateProduct(Product product, User user) {
		
		DaoFactory.getProductDao().update(product);
		return false;
	}

	@Override
	public boolean createOrder(Order order, User user) {
		
		order.setOwner(user);
		user.getOrders().add(order);
		
		DaoFactory.getOrderDao().save(order);
		
		return false;
	}

	@Override
	public boolean deleteOrder(Order order, User user) {
		
		DaoFactory.getOrderDao().delete(order);
		return false;
	}

	@Override
	public boolean updateOrder(Order order, User user) {
		
		DaoFactory.getOrderDao().delete(order);
		return false;
	}

	@Override
	public boolean createUser(User user) {
		
		return false;
	}

}
