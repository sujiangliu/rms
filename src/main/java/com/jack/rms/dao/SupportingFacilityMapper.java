package com.jack.rms.dao;

import com.jack.rms.model.SupportingFacility;

public interface SupportingFacilityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SupportingFacility record);

    int insertSelective(SupportingFacility record);

    SupportingFacility selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SupportingFacility record);

    int updateByPrimaryKey(SupportingFacility record);
}