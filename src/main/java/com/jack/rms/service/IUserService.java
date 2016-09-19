package com.jack.rms.service;

import com.jack.rms.dao.UserMapper;
import com.jack.rms.model.User;

public interface IUserService {

	public User getUserById(String id);
	
	public User getUserByUsername(String username);
	
	public int saveUser(User user);
	
	public UserMapper getUserMapper();
}
