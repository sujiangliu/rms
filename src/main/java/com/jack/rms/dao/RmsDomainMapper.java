package com.jack.rms.dao;

import java.util.List;

import com.jack.rms.model.RmsDomain;
import com.jack.rms.model.request.RmsDomainQueryParam;

public interface RmsDomainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RmsDomain record);

    int insertSelective(RmsDomain record);

    RmsDomain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RmsDomain record);

    int updateByPrimaryKey(RmsDomain record);
    
    List<RmsDomain> selectAllRmsDomains(RmsDomainQueryParam rmsDomainQueryParam);
    
    int countRmsDomainsByPage(RmsDomainQueryParam rmsDomainQueryParam);
}