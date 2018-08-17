package com.nicolas.pos.utilities;

import com.nicolas.pos.dao.DaoFactory;
import com.nicolas.pos.model.User;

public class LoginController {

	private static User loggedInUser;
	
	public static User getLoggedInUser() {
		
		return loggedInUser;
		
	}
	
	public static void setLoggedInUser(User user) {
		
		loggedInUser = user;
		
	}
	
	public static boolean login(String username, String password) {
		
		User user = DaoFactory.getUserDao().getUser(username,password);
		
		if (user != null) {
			
			setLoggedInUser(user);
			
			return true;
			
		}
		
		return false;
		
	}
		
}
