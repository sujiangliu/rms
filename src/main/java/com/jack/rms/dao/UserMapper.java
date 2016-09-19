package com.jack.rms.dao;

import com.jack.rms.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);
    
    User selectByUsername(String name);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}