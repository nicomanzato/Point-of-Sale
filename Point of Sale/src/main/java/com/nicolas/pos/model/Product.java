package com.nicolas.pos.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity()
@Table( name = "PRODUCTS")
public class Product {
	
	
	private Long productId;
	private String name;
	private Float price;
	
	public Product() { super(); }
	
	public Product(Long id, String name, float price) {
		super();
		this.setProductId(id);
		this.setName(name);
		this.setPrice(price);
	}
	
	public Product(String name, float price) {
		
		super();
		this.setName(name);
		this.setPrice(price);
		
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="PRODUCT_ID")
	public Long getProductId() {
		return productId;
	}
	
	public void setProductId(Long id) {
		this.productId = id;
	}
	
	@Column(name="PRODUCT_NAME", nullable=false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="PRODUCT_PRICE", nullable=false)
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}

	public String toString() {
		
		return this.getName() + " $" + this.getPrice();
		
	}
	
}
