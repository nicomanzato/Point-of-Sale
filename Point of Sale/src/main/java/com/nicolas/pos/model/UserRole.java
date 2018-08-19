package com.nicolas.pos.model;


import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import com.nicolas.pos.dao.DaoFactory;
import com.nicolas.pos.utilities.LoginController;

@Entity  
@Table(name = "USER_ROLES")  
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="USER_ROLE_NAME", discriminatorType = DiscriminatorType.STRING) 
public abstract class UserRole {
	
	static final int Manager = 1;
	static final int Cashier = 2;

	private Long id;
	
	public abstract boolean canCreateProduct();
	public abstract boolean canDeleteProduct();
	public abstract boolean canUpdateProduct();
	
	public abstract boolean canCreateOrder();
	public abstract boolean canDeleteOrder();
	public abstract boolean canUpdateOrder();
	public abstract boolean canAccessAllOrders();
	
	public abstract boolean canCreateUser();
	
	@Transient
	public abstract boolean isManager();
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="USER_ROLE_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean createProduct(Product product) {
		
		if (this.canCreateProduct()) {
			
			DaoFactory.getProductDao().save(product);
			
			return true;
		
		}
		
		return false;
	}

	public boolean deleteProduct(Product product) {
		
		if (this.canDeleteProduct()) {
			
			DaoFactory.getProductDao().delete(product);
			return false;
			
		} 
			
		return false;
	}

	public boolean updateProduct(Product product) {
		
		if (this.canUpdateProduct()) {
			
			DaoFactory.getProductDao().update(product);
			return false;
			
		}
		
		return false;
	}

	public boolean createOrder(Order order) {
		
		if (this.canCreateOrder()) {
			
			if ( !LoginController.getLoggedInUser().getOrders().contains(order) ) LoginController.getLoggedInUser().addOrder(order);
			
			order.setCreatedByUser(LoginController.getLoggedInUser());
			DaoFactory.getOrderDao().save(order);
					
			return true;
		}
		
		return false;
	}

	public boolean deleteOrder(Order order) {
		
		if ( this.canDeleteOrder()) {
			
			User orderOwner = order.getCreatedByUser();
			
			orderOwner.getOrders().remove(order);
									
			DaoFactory.getUserDao().update(orderOwner);
		
			return true;
		}
		
		return false;
	}

	public boolean updateOrder(Order order) {
		
		if (this.canUpdateOrder()) {
			
			DaoFactory.getOrderDao().update(order);
			return true;
		}
		
		return false;
	}

	public Order getOrderById( Long orderId) {
		
		return DaoFactory.getOrderDao().getOrder(orderId);
		
	}
	
	public boolean createUser(User user) {
		
		if (this.canCreateUser()) {
			
			DaoFactory.getUserDao().save(user);
			return true;
			
		}
		
		return false;
		
	}
	
}
