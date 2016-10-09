package com.jack.rms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.rms.common.SpringContextUtil;
import com.jack.rms.dao.SupportingFacilityMapper;
import com.jack.rms.model.SupportingFacility;
import com.jack.rms.service.ISupportingFacilityService;

@Service
public class SupportingFacilityService implements ISupportingFacilityService {
	
	@Autowired
	private SupportingFacilityMapper supportingFacilityMapper;
	
	public SupportingFacility getSupportingFacilityById(int id) {
		SupportingFacility supportingFacility = supportingFacilityMapper.selectByPrimaryKey(id);
		return supportingFacility;
	}
	
	public int saveSupportingFacility(SupportingFacility supportingFacility) {
		
		if (null == supportingFacilityMapper) {
			supportingFacilityMapper = SpringContextUtil.getBean("supportingFacilityMapper");
		}
		
		return supportingFacilityMapper.insertSelective(supportingFacility);
	}
	
	public int updateSupportingFacility(SupportingFacility supportingFacility) {
		return supportingFacilityMapper.updateByPrimaryKeySelective(supportingFacility);
	}
	
	public int delSupportingFacilityByHouseId(Integer houseId){
		return supportingFacilityMapper.deleteByHouseId(houseId);
	}
	
	@Override
	public List<SupportingFacility> getSupportingFacilitysByHouseId(Integer houseId) {
		return supportingFacilityMapper.selectSupportingFacilitysByHouseId(houseId);
	}
}
