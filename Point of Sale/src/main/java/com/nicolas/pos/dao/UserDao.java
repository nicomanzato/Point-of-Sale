package com.nicolas.pos.dao;

import java.util.List;
import java.util.Observer;

import com.nicolas.pos.model.User;
import com.nicolas.pos.model.UserRole;

public interface UserDao {
	
	public void save(User user);
	public void update(User user);
	public void delete(User user);
	public List<User> getUsers();
	public User getUser(long idUser);
	public User getUser(String username, String password);
	public UserRole getUserRole(int role);
	public void addObserver(Observer observer);

}
