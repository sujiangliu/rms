package com.jack.rms.service;

import java.util.List;

import com.jack.rms.model.SupportingFacility;

public interface ISupportingFacilityService {

	public int saveSupportingFacility(SupportingFacility supportingFacility);
	
	public int delSupportingFacilityByHouseId(Integer houseId);
	
	public List<SupportingFacility> getSupportingFacilitysByHouseId(Integer houseId);
}
