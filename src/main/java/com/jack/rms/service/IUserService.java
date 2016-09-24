package com.jack.rms.service;

import java.util.List;

import com.jack.rms.dao.UserMapper;
import com.jack.rms.model.User;

public interface IUserService {

	public User getUserById(int id);
	
	public User getUserByUsername(String username);
	
	public int saveUser(User user);
	
	public UserMapper getUserMapper();
	
	public List<User> getUsersByPage();
}
