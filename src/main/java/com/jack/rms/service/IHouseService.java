package com.jack.rms.service;

import java.util.List;

import com.jack.rms.dao.HouseMapper;
import com.jack.rms.model.House;
import com.jack.rms.model.request.HouseQueryParam;

public interface IHouseService {

	public House getHouseById(int id);
	
	public int saveHouse(House user);
	
	public int updateHouse(House user);
	
	public int delHouse(Integer userId);
	
	public HouseMapper getHouseMapper();
	
	public List<House> getHousesByPage(HouseQueryParam userQueryParam);
	
	public int countHousesByPage(HouseQueryParam userQueryParam);
}
