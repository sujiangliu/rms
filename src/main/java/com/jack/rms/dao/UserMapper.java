package com.jack.rms.dao;

import java.util.List;

import com.jack.rms.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    
    User selectByUsername(String username);
    
    List<User> selectAllUsers();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}