package com.nicolas.pos.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
		
		User user = DaoFactory.getUserDao().getUser(username,encryptPassword(password));
		
		if (user != null) {
			
			setLoggedInUser(user);
			
			return true;
			
		}
		
		return false;
		
	}
	
	public static boolean checkUsernameInUse(String username) {
		
		for (User user : DaoFactory.getUserDao().getUsers()) {
			
			if ( user.getUsername() == username ) return true;
			
		}
		
		return false;
		
	}
	
	public static String encryptPassword(String password) {
		
		MessageDigest messageDigest;
		String encryptedString = "";
		
		try {
			
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes());
			encryptedString = new String(messageDigest.digest());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return encryptedString;
		
	}
		
}
