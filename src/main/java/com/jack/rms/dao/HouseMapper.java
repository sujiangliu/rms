package com.jack.rms.dao;

import java.util.List;

import com.jack.rms.model.House;
import com.jack.rms.model.request.HouseQueryParam;

public interface HouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
    
    List<House> selectAllHouses(HouseQueryParam houseQueryParam);
    
    int countHousesByPage(HouseQueryParam houseQueryParam);
}