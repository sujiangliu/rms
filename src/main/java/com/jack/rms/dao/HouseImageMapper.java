package com.jack.rms.dao;

import java.util.List;

import com.jack.rms.model.HouseImage;

public interface HouseImageMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByHouseId(Integer houseId);

    int insert(HouseImage record);

    int insertSelective(HouseImage record);

    HouseImage selectByPrimaryKey(Integer id);
    
    List<HouseImage> selectByHouseId(Integer houseId);

    int updateByPrimaryKeySelective(HouseImage record);

    int updateByPrimaryKey(HouseImage record);
}