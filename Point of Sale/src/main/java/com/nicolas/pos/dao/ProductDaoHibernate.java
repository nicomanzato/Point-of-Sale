package com.nicolas.pos.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	    
		List<Product> products = null;
		
		try {
		
			Session session = getSession();
				
			CriteriaQuery<Product> criteria = session.getCriteriaBuilder().createQuery( Product.class );
			Root<Product> productRoot = criteria.from(Product.class);
			criteria.select(productRoot);
			
			criteria.orderBy(session.getCriteriaBuilder().asc(productRoot.get("productId")));
			criteria.where( session.getCriteriaBuilder().equal( productRoot.get("deleted"), false));
			
			products = session.createQuery( criteria ).list();
			
			session.close();
	    
		} catch( NoResultException e) {
			
		} 
		
	    return products;
		
	}

	public void update(Product product) {
		
		super.update(product);
		
		this.setChanged();
		this.notifyObservers();
		
	}

	public void delete(Product product) {
		
		product.setDeleted(true);
		super.update(product);
	    
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
