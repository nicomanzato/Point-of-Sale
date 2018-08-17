package com.nicolas.pos.dao;

import java.util.List;
import com.nicolas.pos.model.User;

public interface UserDao {
	
	public void save(User user);
	public void update(User user);
	public void delete(User user);
	public List<User> getUsers();
	public User getUser(long idUser);
	public User getUser(String username, String password);

}
