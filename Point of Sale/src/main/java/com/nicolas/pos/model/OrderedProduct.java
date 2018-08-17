package com.nicolas.pos.model;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity()
@Table( name = "PRODUCT_ORDEREDS")
public class OrderedProduct {

	private Long productOrderedId;
	private Product product;
	private int quantity;
	private float price;
	
	public OrderedProduct() { super(); }
	
	public OrderedProduct(Product product, int quantity, float price) {
		super();
		this.setProduct(product);
		this.setQuantity(quantity);
		this.setPrice(price);
	}
	
	public OrderedProduct(Product product, int quantity) {
		
		super();
		this.setProduct(product);
		this.setQuantity(quantity);
		this.setPrice(product.getPrice());
		
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="PRODUCT_ORDERED_ID")
	public Long getProductOrderedId() {
		return productOrderedId;
	}
	public void setProductOrderedId(Long productOrderedId) {
		this.productOrderedId = productOrderedId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Column(name="PRODUCT_ORDERED_QUANTITY", nullable=false)
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Column(name="PRODUCT_ORDERED_PRICE", nullable=false)
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public void addProduct(){
		
		this.setQuantity(this.getQuantity() + 1);
		
	}
	
	public float calculateSubTotal() {
		
		return this.getPrice() * this.getQuantity();
		
	}
	
	public boolean decreaseProduct(){
		
		if (this.getQuantity() > 0){ 
			
			this.setQuantity(this.getQuantity() - 1); 
			
			return true;
			
		}else{
			
			return false;
			
		}
		
		
		
	}
	
}
