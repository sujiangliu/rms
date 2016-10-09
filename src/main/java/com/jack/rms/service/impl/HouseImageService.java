package com.jack.rms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.rms.common.SpringContextUtil;
import com.jack.rms.dao.HouseImageMapper;
import com.jack.rms.model.HouseImage;
import com.jack.rms.service.IHouseImageService;

@Service
public class HouseImageService implements IHouseImageService {
	
	@Autowired
	private HouseImageMapper houseImageMapper;
	
	public HouseImage getHouseImageById(int id) {
		HouseImage houseImage = houseImageMapper.selectByPrimaryKey(id);
		return houseImage;
	}
	
	public int saveHouseImage(HouseImage houseImage) {
		
		if (null == houseImageMapper) {
			houseImageMapper = SpringContextUtil.getBean("houseImageMapper");
		}
		
		return houseImageMapper.insertSelective(houseImage);
	}
	
	public int updateHouseImage(HouseImage houseImage) {
		return houseImageMapper.updateByPrimaryKeySelective(houseImage);
	}
	
	public int delHouseImageByHouseId(Integer houseId){
		return houseImageMapper.deleteByHouseId(houseId);
	}
	
	@Override
	public List<HouseImage> getHouseImagesByHouseId(Integer houseId) {
		return houseImageMapper.selectByHouseId(houseId);
	}
}
