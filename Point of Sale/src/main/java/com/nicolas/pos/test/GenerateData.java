package com.nicolas.pos.test;

import com.nicolas.pos.dao.DaoFactory;
import com.nicolas.pos.utilities.LoginController;

public class GenerateData {
	
	public static void main(String args[]) {
		
		int cantProducts = 4;
		
		int cantOrders = 10;
		
		DaoFactory.getUserDao().save(GenerateUser.GenerateManager());
		
		DaoFactory.getUserDao().save(GenerateUser.GenerateCashier());
		
		LoginController.setLoggedInUser(DaoFactory.getUserDao().getUser(1));

		for(int i=0; i < cantProducts; i++) {
			
			DaoFactory.getProductDao().save(GenerateProduct.generateRandomProduct());
			
		}
		
		for(int i=0; i < cantOrders; i++) {
			
			LoginController.getLoggedInUser().createOrder(GenerateOrder.GenerateRandomOrder());
						
		}
		
		
	}

}
