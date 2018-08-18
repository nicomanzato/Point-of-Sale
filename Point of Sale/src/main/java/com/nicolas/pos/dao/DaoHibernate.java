package com.nicolas.pos.dao;

import java.util.Observable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DaoHibernate extends Observable{
	
	protected Session getSession() {
		
		return HibernateUtil.getSessionFactory().openSession();
	}
	
	public void save(Object obj) {
		
		Session session = getSession();
    	Transaction transaction = null;		
		try {
  	      transaction = session.beginTransaction();
  	      session.save(obj);
  	      transaction.commit();
  	    } catch (HibernateException e) {
  	      transaction.rollback();
  	      e.printStackTrace();
  	    } finally {
  	      session.close();
  	    }
		
	}
	
	public void update(Object obj) {
		
		Session session = getSession();
    	Transaction transaction = null;		
		try {
  	      transaction = session.beginTransaction();
  	      session.merge(obj);
  	      transaction.commit();
  	    } catch (HibernateException e) {
  	      transaction.rollback();
  	      e.printStackTrace();
  	    } finally {
  	      session.close();
  	    }
		
	}
	
	public void delete(Object obj) {
		
		Session session = getSession();
    	Transaction transaction = null;		
		try {
  	      transaction = session.beginTransaction();
  	      session.delete(obj);
  	      transaction.commit();
  	    } catch (HibernateException e) {
  	      transaction.rollback();
  	      e.printStackTrace();
  	    } finally {
  	      session.close();
  	    }
		
	}

}
