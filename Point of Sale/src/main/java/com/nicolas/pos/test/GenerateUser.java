package com.nicolas.pos.test;

import com.nicolas.pos.model.CashierUserRole;
import com.nicolas.pos.model.ManagerUserRole;
import com.nicolas.pos.model.User;

public class GenerateUser {
	
	public static void GenerateUserRoles() {
		
		
		
	}

	public static User GenerateManager() {
		
		ManagerUserRole userRole = new ManagerUserRole();
		User user = new User("admin", "admin", userRole);
		
		return user;
		
	}
	
	public static User GenerateCashier() {
		
		CashierUserRole userRole = new CashierUserRole();
		User user = new User("cashier", "cashier", userRole);
		
		return user;
		
	} 
	
}
