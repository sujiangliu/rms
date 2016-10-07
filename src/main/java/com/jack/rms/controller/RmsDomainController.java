package com.jack.rms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jack.rms.common.core.BaseController;
import com.jack.rms.model.PageResponse;
import com.jack.rms.model.RmsDomain;
import com.jack.rms.model.request.RmsDomainQueryParam;
import com.jack.rms.service.IRmsDomainService;

@Controller
@RequestMapping("/rmsDomain")
public class RmsDomainController extends BaseController {
	
	@Autowired
	private IRmsDomainService rmsDomainService; 
	
	@RequestMapping("rmsDomainPage")
	public String rmsDomainPage() {
		return "/rmsDomain/rmsDomainList";
	}
	
	@ResponseBody
	@RequestMapping("list")
	public PageResponse list(RmsDomainQueryParam rmsDomainQueryParam) {
		
		if (null == rmsDomainQueryParam.getPageNumber()) {
			rmsDomainQueryParam.setPageNumber(0);
		}
		if (null == rmsDomainQueryParam.getPageSize()) {
			rmsDomainQueryParam.setPageSize(10);
		}
		rmsDomainQueryParam.setStartNumber((rmsDomainQueryParam.getPageNumber() - 1) * rmsDomainQueryParam.getPageSize());
		
		int total = rmsDomainService.countRmsDomainsByPage(rmsDomainQueryParam);
		List<RmsDomain> rmsDomains = rmsDomainService.getRmsDomainsByPage(rmsDomainQueryParam);
		
		PageResponse pageResponse = new PageResponse();
		pageResponse.setTotal(total);
		pageResponse.setPageNumber(rmsDomainQueryParam.getPageNumber());
		pageResponse.setResults(rmsDomains);
		
		return pageResponse;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(RmsDomain rmsDomain) {
		
		if (null == rmsDomain) {
			return "用户信息不正确";
		}
		int i = rmsDomainService.saveRmsDomain(rmsDomain);
		if (i < 1) {
			return "新增失败";
		}
		return "200";
	}
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(RmsDomain rmsDomain) {
		
		if (null == rmsDomain) {
			return "用户信息不正确";
		}
		
		int i = rmsDomainService.updateRmsDomain(rmsDomain);
		if (i < 1) {
			return "修改失败";
		}
		return "200";
	}
	@RequestMapping("/del")
	@ResponseBody
	public String del(Integer rmsDomainId) {
		
		if (null == rmsDomainId) {
			return "请选择需要删除的用户";
		}
		
		int i = rmsDomainService.delRmsDomain(rmsDomainId);
		if (i < 1) {
			return "删除失败";
		}
		return "200";
	}
}
