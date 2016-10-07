package com.jack.rms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jack.rms.common.core.BaseController;
import com.jack.rms.model.House;
import com.jack.rms.model.PageResponse;
import com.jack.rms.model.request.HouseQueryParam;
import com.jack.rms.service.IHouseService;

@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {
	
	@Autowired
	private IHouseService houseService; 

	@RequestMapping("/housePage")
	public String housePage() {
		return "/house/houseList";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HouseQueryParam houseQueryParam) {
		
		if (null == houseQueryParam.getPageNumber()) {
			houseQueryParam.setPageNumber(0);
		}
		if (null == houseQueryParam.getPageSize()) {
			houseQueryParam.setPageSize(10);
		}
		houseQueryParam.setStartNumber((houseQueryParam.getPageNumber() - 1) * houseQueryParam.getPageSize());
		
		int total = houseService.countHousesByPage(houseQueryParam);
		List<House> houses = houseService.getHousesByPage(houseQueryParam);
		
		PageResponse pageResponse = new PageResponse();
		pageResponse.setTotal(total);
		pageResponse.setPageNumber(houseQueryParam.getPageNumber());
		pageResponse.setResults(houses);
		
		return pageResponse;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(House house) {
		
		if (null == house) {
			return "房屋信息不正确";
		}
		
		int i = houseService.saveHouse(house);
		if (i < 1) {
			return "新增失败";
		}
		return "200";
	}
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(House house) {
		
		if (null == house) {
			return "房屋信息不正确";
		}
		
		int i = houseService.updateHouse(house);
		if (i < 1) {
			return "修改失败";
		}
		return "200";
	}
	@RequestMapping("/del")
	@ResponseBody
	public String del(Integer houseId) {
		
		if (null == houseId) {
			return "请选择需要删除的房屋";
		}
		
		int i = houseService.delHouse(houseId);
		if (i < 1) {
			return "删除失败";
		}
		return "200";
	}
}
