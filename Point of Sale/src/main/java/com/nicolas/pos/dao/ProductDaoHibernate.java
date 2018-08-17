package com.nicolas.pos.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nicolas.pos.model.Product; 

public class ProductDaoHibernate extends DaoHibernate implements ProductDao{
		
	public ProductDaoHibernate(){
				
	}
	
	public void save(Product product) {
		
		super.save(product);
		
		this.setChanged();
		this.notifyObservers();
		
	}
	
	public List<Product> getProducts(){
		
		Session session = this.getSession();
		Transaction tx = null;
		List<Product> products = null;
		
		try {
		
			tx = session.beginTransaction();
			products = (List<Product>) session.createQuery("From Product").list();
			tx.commit();
			
		} catch (HibernateException e) {
	         
			if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	   
		} finally {
	         session.close(); 
	    }
	    
	    return products;
		
	}

	public void update(Product product) {
		
		super.update(product);
		
		this.setChanged();
		this.notifyObservers();
		
	}

	public void delete(Product product) {
		
		super.delete(product);
	    
		this.setChanged();
	    this.notifyObservers();
		
	}

	public Product getProduct(long idProduct) {
		
		Session session = this.getSession();
		Transaction tx = null;
		Product product = null;
		
		try {
		
			tx = session.beginTransaction();
			product = getSession().get(Product.class, idProduct);
			tx.commit();
			
		} catch (HibernateException e) {
	         
			if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	   
		} finally {
	         session.close(); 
	    }
	    
	    return product;
		
	}
}
