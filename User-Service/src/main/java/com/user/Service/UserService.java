package com.user.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.Model.User;


public interface UserService {

	public User getById(String id);
	public List<User> getAllUsers();
	public User saveUser(User user);
	public User deleteUser(String id);
	
	
}
