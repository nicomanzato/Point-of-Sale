package com.nicolas.pos.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nicolas.pos.model.User;
import com.nicolas.pos.model.UserRole;

public class UserDaoHibernate extends DaoHibernate implements UserDao {

	public void save(User user) {
		super.save(user);
		
		this.setChanged();
		this.notifyObservers();		
	}

	public void update(User user) {
		
		super.update(user);
		
		this.setChanged();
		this.notifyObservers();		
	}
	
	public void merge(User user) {
		
		super.merge(user);
		
	}

	public void delete(User user) {

		super.delete(user);
		
		this.setChanged();
		this.notifyObservers();
	}

	public List<User> getUsers() {
		Session session = this.getSession();
		Transaction tx = null;
		List<User> users = null;
		
		try {
		
			tx = session.beginTransaction();
			
			users = (List<User>) session.createQuery("From User").list();
			tx.commit();
			
		} catch (HibernateException e) {
	         
			if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	   
		} finally {
	         session.close(); 
	    }
	    
	    return users;
	}

	public User getUser(long idUser) {
		
		Session session = this.getSession();
		Transaction tx = null;
		User user = null;
		
		try {
		
			tx = session.beginTransaction();
			user = getSession().get(User.class, idUser);
			tx.commit();
			
		} catch (HibernateException e) {
	         
			if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	   
		} finally {
	         session.close(); 
	    }
	    
	    return user;
	}
	
	public User getUser(String username, String password) {
		
		User user = null;
		
		try {
		
			Session session = getSession();
				
			CriteriaQuery<User> criteria = session.getCriteriaBuilder().createQuery( User.class );
			Root<User> userRoot = criteria.from(User.class);
			criteria.select(userRoot);
			criteria.where( session.getCriteriaBuilder().equal( userRoot.get("username") , username), getSession().getCriteriaBuilder().equal( userRoot.get("password") , password) );
			
			user = session.createQuery( criteria ).getSingleResult();
			
			session.close();
	    
		} catch( NoResultException e) {
			
		} 
		
	    return user;
	}

	public UserRole getUserRole(int role) {

		UserRole userRole = null;
		
		try {
		
			Session session = getSession();
				
			CriteriaQuery<UserRole> criteria = session.getCriteriaBuilder().createQuery( UserRole.class );
			Root<UserRole> userRoleRoot = criteria.from(UserRole.class);
			criteria.select(userRoleRoot);
			criteria.where( session.getCriteriaBuilder().equal( userRoleRoot.get("id") , role) );
			
			userRole = session.createQuery( criteria ).getSingleResult();
			
			session.close();
	    
		} catch( NoResultException e) {
			
		} 
		
	    return userRole;
	}

}
