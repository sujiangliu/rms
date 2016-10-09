package com.jack.rms.dao;

import java.util.List;

import com.jack.rms.model.SupportingFacility;

public interface SupportingFacilityMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByHouseId(Integer houseId);

    int insert(SupportingFacility record);

    int insertSelective(SupportingFacility record);

    SupportingFacility selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SupportingFacility record);

    int updateByPrimaryKey(SupportingFacility record);
    
    List<SupportingFacility> selectSupportingFacilitysByHouseId(Integer houseId);
}