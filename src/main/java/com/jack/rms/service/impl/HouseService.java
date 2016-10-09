package com.jack.rms.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.rms.common.SpringContextUtil;
import com.jack.rms.dao.HouseMapper;
import com.jack.rms.dao.SupportingFacilityMapper;
import com.jack.rms.model.House;
import com.jack.rms.model.SupportingFacility;
import com.jack.rms.model.request.HouseQueryParam;
import com.jack.rms.service.IHouseService;

@Service
@Transactional
public class HouseService implements IHouseService {
	
	@Autowired
	private HouseMapper houseMapper;
	
	@Autowired
	private SupportingFacilityMapper supportingFacilityMapper;
	
	public House getHouseById(int id) {
		House house = houseMapper.selectByPrimaryKey(id);
		return house;
	}
	
	public int saveHouse(House house) {
		int r = houseMapper.insertSelective(house);
		List<SupportingFacility> supportingFacilities = house.getSupportingFacilities();
		for (SupportingFacility sf : supportingFacilities) {
			sf.sethId(house.getId());
			supportingFacilityMapper.insert(sf);
		}
		return r;
	}
	
	public int updateHouse(House house) {
		
		supportingFacilityMapper.deleteByHouseId(house.getId());
		List<SupportingFacility> supportingFacilities = house.getSupportingFacilities();
		for (SupportingFacility sf : supportingFacilities) {
			sf.sethId(house.getId());
			supportingFacilityMapper.insert(sf);
		}
		
		return houseMapper.updateByPrimaryKeySelective(house);
	}
	
	public int delHouse(Integer houseId){
		return houseMapper.deleteByPrimaryKey(houseId);
	}
	
	public HouseMapper getHouseMapper() {
		return houseMapper;
	}

	@Override
	public List<House> getHousesByPage(HouseQueryParam houseQueryParam) {
		return houseMapper.selectAllHouses(houseQueryParam);
	}
	
	@Override
	public int countHousesByPage(HouseQueryParam houseQueryParam) {
		
		return houseMapper.countHousesByPage(houseQueryParam);
	}
}
