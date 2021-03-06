package com.nicolas.pos.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.nicolas.pos.dao.DaoFactory;
import com.nicolas.pos.utilities.LoginController;

@Entity()
@Table( name = "USERS")
public class User {
	
	private Long userId;
	private String username;
	private String password;
	
	private List<Order> orders;
	
	private UserRole userRole;
	
	public User() {
		
		super();
		this.orders = new ArrayList<Order>();
		
	}

	public User(Long id, String name, String password, List<Order> orders, UserRole userRole) {
		super();
		this.userId = id;
		this.username = name;
		this.password = LoginController.encryptPassword(password);
		this.orders = orders;
		this.userRole = userRole;
	}

	public User(String name, String password, UserRole userRole) {
		super();
		this.username = name;
		this.password = LoginController.encryptPassword(password);
		this.userRole = userRole;
		this.orders = new ArrayList<Order>();
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long id) {
		this.userId = id;
	}

	@Column(name="USER_USERNAME", nullable=false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="USER_PASSWORD", nullable=false)
	public String getPassword() {
		return password;
	}

	public boolean changePassword( String encryptedOldPassword, String newPassword ) {
		
		
		if (this.getPassword().equals(encryptedOldPassword)) { 
			
			this.setPassword(LoginController.encryptPassword(newPassword)); 
			
			DaoFactory.getUserDao().update(this);
			
			return true;
			
		}
		
		return false;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="createdByUser", orphanRemoval = true)
	@Cascade(CascadeType.ALL)
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order) {
		
		this.getOrders().add(order);
		
	}
	
	public Order getOrderById(Long orderId) {
		
		for (Order order : this.getOrders()) {
			
			if (order.getOrderId() == orderId) return order;
			
		}
		
		return this.getUserRole().getOrderById(orderId);
		
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ROLE_ID", nullable = false)
	@Cascade(CascadeType.ALL)
	private UserRole getUserRole() {
		return userRole;
	}

	private void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	
	
	public boolean createProduct(Product product) {
		
		return this.getUserRole().createProduct(product);
		
	}

	public boolean deleteProduct(Product product) {
		
		return this.getUserRole().deleteProduct(product);
	}

	public boolean updateProduct(Product product) {
		
		return this.getUserRole().updateProduct(product);
	}

	public boolean createOrder(Order order) {
		
		return this.getUserRole().createOrder(order);
	}

	public boolean deleteOrder(Order order) {
		
		return this.getUserRole().deleteOrder(order);
	}

	public boolean updateOrder(Order order) {
		
		return this.getUserRole().updateOrder(order);
	}
	
	public boolean createCashier(String username, String password) {
		
		if (!LoginController.checkUsernameInUse(username)) {
		
			User user = new User (username, password, DaoFactory.getUserDao().getUserRole(UserRole.Cashier));
		
			return this.getUserRole().createUser(user);
		
		}
		
		return false;
		
	}
	
	public boolean createManager(String username, String password) {
		
		User user = new User (username, password, DaoFactory.getUserDao().getUserRole(UserRole.Manager));
		
		return this.getUserRole().createUser(user);
		
	}
	
	
	
	@Transient
	public boolean isManager() {
		
		return this.getUserRole().isManager();
		
	}
	
	public boolean canCreateProduct() { return this.getUserRole().canCreateProduct(); }
	
	public boolean canDeleteProduct() { return this.getUserRole().canDeleteProduct(); }
	
	public boolean canUpdateProduct() { return this.getUserRole().canUpdateProduct(); }
	
	public boolean canCreateOrder() { return this.getUserRole().canCreateOrder(); }
	
	public boolean canDeleteOrder() { return this.getUserRole().canDeleteOrder(); }
	
	public boolean canUpdateOrder() { return this.getUserRole().canUpdateOrder(); }
	
	public boolean canAccessAllOrders() { return this.getUserRole().canAccessAllOrders(); }
	
	public boolean canCreateUser() { return this.getUserRole().canCreateUser(); }
	
	
	
}
