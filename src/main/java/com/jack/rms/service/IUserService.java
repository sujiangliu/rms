package com.jack.rms.service;

import java.util.List;

import com.jack.rms.dao.UserMapper;
import com.jack.rms.model.User;
import com.jack.rms.model.request.UserQueryParam;

public interface IUserService {

	public User getUserById(int id);
	
	public User getUserByUsername(String username);
	
	public User getUserByMobile(String mobile);
	
	public int saveUser(User user);
	
	public UserMapper getUserMapper();
	
	public List<User> getUsersByPage(UserQueryParam userQueryParam);
	
	public int countUsersByPage(UserQueryParam userQueryParam);
}
