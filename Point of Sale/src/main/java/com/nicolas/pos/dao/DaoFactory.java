package com.nicolas.pos.dao;

public class DaoFactory {

	private static ProductDao productDao = null;
	private static OrderDao orderDao = null;
	private static UserDao userDao = null;
	
	public static ProductDao getProductDao(){
		
		if (productDao == null) productDao = new ProductDaoHibernate(); 
		
		return productDao;
		
	}
	
	public static OrderDao getOrderDao(){
		
		if (orderDao == null) orderDao = new OrderDaoHibernate(); 
		
		return orderDao;
		
	}
	
	public static UserDao getUserDao(){
		
		if (userDao == null) userDao = new UserDaoHibernate(); 
		
		return userDao;
		
	}
	
}