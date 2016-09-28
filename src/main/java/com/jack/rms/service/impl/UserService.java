package com.jack.rms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.rms.common.SpringContextUtil;
import com.jack.rms.dao.UserMapper;
import com.jack.rms.model.User;
import com.jack.rms.model.request.UserQueryParam;
import com.jack.rms.service.IUserService;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public User getUserById(int id) {
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}
	
	public User getUserByUsername(String username) {
		User user = userMapper.selectByUsername(username);
		return user;
	}
	
	public int saveUser(User user) {
		
		if (null == userMapper) {
			userMapper = SpringContextUtil.getBean("userMapper");
		}
		
		return userMapper.insertSelective(user);
	}
	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Override
	public List<User> getUsersByPage(UserQueryParam userQueryParam) {
		return userMapper.selectAllUsers(userQueryParam);
	}
	
	@Override
	public int countUsersByPage(UserQueryParam userQueryParam) {
		
		return userMapper.countUsersByPage(userQueryParam);
	}
}
