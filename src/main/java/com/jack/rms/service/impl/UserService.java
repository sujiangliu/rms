package com.jack.rms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.rms.common.SpringContextUtil;
import com.jack.rms.dao.UserMapper;
import com.jack.rms.model.User;
import com.jack.rms.service.IUserService;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public User getUserById(String id) {
		User user = userMapper.selectByPrimaryKey(id);
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
}
