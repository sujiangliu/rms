package com.jack.rms.dao;

import com.jack.rms.model.UserHobby;

public interface UserHobbyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserHobby record);

    int insertSelective(UserHobby record);

    UserHobby selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserHobby record);

    int updateByPrimaryKey(UserHobby record);
}