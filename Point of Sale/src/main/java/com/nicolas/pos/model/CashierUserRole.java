package com.nicolas.pos.model;

import javax.persistence.Entity;
import com.nicolas.pos.dao.DaoFactory;
import javax.persistence.*;

@Entity  
@DiscriminatorValue("Cashier") 
public class CashierUserRole extends UserRole{

	@Override
	public boolean createProduct(Product product, User user) {

		return false;
	}

	@Override
	public boolean deleteProduct(Product product, User user) {

		return false;
	}

	@Override
	public boolean updateProduct(Product product, User user) {

		return false;
	}

	@Override
	public boolean createOrder(Order order, User user) {

		DaoFactory.getOrderDao().save(order);
		return false;
	}

	@Override
	public boolean deleteOrder(Order order, User user) {

		return false;
	}

	@Override
	public boolean updateOrder(Order order, User user) {

		return false;
	}

	@Override
	public boolean createUser(User user) {

		return false;
	}

}
