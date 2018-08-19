package com.nicolas.pos.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nicolas.pos.model.Order;

public class OrderDaoHibernate extends DaoHibernate implements OrderDao{
		
	public OrderDaoHibernate(){}
	
	public void save(Order order) {
		super.save(order);
		
		this.setChanged();
		this.notifyObservers();
		
		
	}

	public void update(Order order) {
			
		super.update(order);
		
		this.setChanged();
		this.notifyObservers();
		
	}

	public void delete(Order order){
		
		//order.setCreatedByUser(null);
		super.update(order);
	      
	    this.setChanged();
	    this.notifyObservers();
	    
	}

	@SuppressWarnings("unchecked")
	public List<Order> getOrders() {
		
		List<Order> orders = null;
		int orderQuantity = 40;
		
		try {
		
			Session session = getSession();
				
			CriteriaQuery<Order> criteria = session.getCriteriaBuilder().createQuery( Order.class );
			Root<Order> orderRoot = criteria.from(Order.class);
			criteria.select(orderRoot);
			
			criteria.orderBy(session.getCriteriaBuilder().desc(orderRoot.get("orderId")));
			
			//criteria.where( session.getCriteriaBuilder().equal( orderRoot.get("deleted"), false));
			
			orders = session.createQuery( criteria ).setMaxResults(orderQuantity).list();
			
			session.close();
	    
		} catch( NoResultException e) {
			
		} 
		
		return orders;
		
	}
	
	public Order getOrder(Long orderId) {
		
		Session session = this.getSession();
		Transaction tx = null;
		Order order = null;
		
		try {
		
			tx = session.beginTransaction();
			order = getSession().get(Order.class, orderId);
			tx.commit();
			
		} catch (HibernateException e) {
	         
			if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	   
		} finally {
	         session.close(); 
	    }
	    
	    return order;
	    
	}


}
