package com.nicolas.pos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity()
@Table( name = "ORDERS")
public class Order extends Observable{
		
	private Long orderId;
	private Date date;
	private List<OrderedProduct> products;
	
	
	public Order() { 
		
		super(); 
		this.setDate(new Date());
		products =  new ArrayList<OrderedProduct>();
				
	}
	
	public Order(Long orderId, Date date, List<OrderedProduct> products) {
		super();
		this.orderId = orderId;
		this.date = date;
		this.products = products;
	}
	
	public Order(Date date, List<OrderedProduct> products) {
		super();
		this.date = date;
		this.products = products;
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="ORDER_ID")
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	@Column(name="ORDER_DATE", nullable=false)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "ORDER_ID", nullable = false)
	@Cascade(CascadeType.ALL)
	public List<OrderedProduct> getProducts() {
		return products;
	}
	public void setProducts(List<OrderedProduct> products) {
		this.products = products;
	}
	
	
	
	public boolean contains(Product product){
		
		for(OrderedProduct auxProduct : this.getProducts()){
			
			if (auxProduct.getProduct().getProductId() == product.getProductId()){
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	public void addProduct(Product product) {
		
		if (!this.contains(product)){ 
			
			OrderedProduct orderedProduct = new OrderedProduct(product,1);
			this.getProducts().add(orderedProduct);
			
			
		}else{
			
			for (OrderedProduct orderedProduct : this.getProducts()){
				
				if (orderedProduct.getProduct().getProductId() == product.getProductId()){ 
					
					orderedProduct.addProduct(); 
					
				}
			}
			
		}
		
		setChanged();
		notifyObservers();
		
	}
	
	public void removeProduct( Product product ) {
		
		OrderedProduct auxOrderedProduct=null;
		
		if (this.contains(product)){ 

			for (OrderedProduct orderedProduct : this.getProducts()){
				
				if (orderedProduct.getProduct().getProductId() == product.getProductId()){ 
					
					auxOrderedProduct = orderedProduct;
					
				}
			}
			
			if (auxOrderedProduct != null){
			
				auxOrderedProduct.decreaseProduct();
				
				if (auxOrderedProduct.getQuantity() == 0){
					
					this.getProducts().remove(auxOrderedProduct);
										
				}
				
				setChanged();
				notifyObservers();
			
			}
		}
		
	}

	@Transient
	public float getPrice() {
		
		float price = 0;
		
		for (OrderedProduct p : products) {
			
			price = price + p.calculateSubTotal();
			
		}
		
		return price;
	}
	
	@Transient
	public boolean isEmpty() {
		
		return products.isEmpty();
		
	}
	
	public String toString() {
		
		return "Order of $" + this.getPrice();
				
	}

}
