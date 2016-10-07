package com.jack.rms.dao;

import com.jack.rms.model.HouseImage;

public interface HouseImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseImage record);

    int insertSelective(HouseImage record);

    HouseImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseImage record);

    int updateByPrimaryKey(HouseImage record);
}