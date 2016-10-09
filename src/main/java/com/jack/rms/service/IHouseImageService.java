package com.jack.rms.service;

import java.util.List;

import com.jack.rms.model.HouseImage;

public interface IHouseImageService {

	public int saveHouseImage(HouseImage houseImage);
	
	public int delHouseImageByHouseId(Integer houseId);
	
	public List<HouseImage> getHouseImagesByHouseId(Integer houseId);
}
