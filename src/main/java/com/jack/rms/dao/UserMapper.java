package com.jack.rms.dao;

import java.util.List;

import com.jack.rms.model.User;
import com.jack.rms.model.request.UserQueryParam;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    
    User selectByUsername(String username);
    
    User selectByMobile(String mobile);
    
    List<User> selectAllUsers(UserQueryParam userQueryParam);
    
    int countUsersByPage(UserQueryParam userQueryParam);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}