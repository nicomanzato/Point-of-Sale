package com.nicolas.pos.model;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity  
@Table(name = "USER_ROLES")  
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="USER_ROLE_NAME", discriminatorType = DiscriminatorType.STRING) 
public abstract class UserRole {

	private Long id;
	
	public abstract boolean createProduct( Product product, User user );
	public abstract boolean deleteProduct( Product product, User user );
	public abstract boolean updateProduct( Product product, User user );
	
	public abstract boolean createOrder( Order order, User user );
	public abstract boolean deleteOrder( Order order, User user );
	public abstract boolean updateOrder( Order order, User user );
	
	public abstract boolean createUser( User user );
	
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
	
	
	
}
