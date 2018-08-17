package com.nicolas.pos.dao;

import java.util.List;
import java.util.Observer;

import com.nicolas.pos.model.Product;


public interface ProductDao {

	public void save(Product product);
	public void update(Product product);
	public void delete(Product product);
	public List<Product> getProducts();
	public Product getProduct(long idProduct);
	public void addObserver(Observer observer);
	
}