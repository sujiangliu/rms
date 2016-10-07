package com.jack.rms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.rms.common.SpringContextUtil;
import com.jack.rms.dao.RmsDomainMapper;
import com.jack.rms.model.RmsDomain;
import com.jack.rms.model.request.RmsDomainQueryParam;
import com.jack.rms.service.IRmsDomainService;

@Service
public class RmsDomainService implements IRmsDomainService {
	
	@Autowired
	private RmsDomainMapper rmsDomainMapper;
	
	public RmsDomain getRmsDomainById(int id) {
		RmsDomain rmsDomain = rmsDomainMapper.selectByPrimaryKey(id);
		return rmsDomain;
	}
	
	public int saveRmsDomain(RmsDomain rmsDomain) {
		
		if (null == rmsDomainMapper) {
			rmsDomainMapper = SpringContextUtil.getBean("rmsDomainMapper");
		}
		
		return rmsDomainMapper.insertSelective(rmsDomain);
	}
	
	public int updateRmsDomain(RmsDomain rmsDomain) {
		return rmsDomainMapper.updateByPrimaryKeySelective(rmsDomain);
	}
	
	public int delRmsDomain(Integer rmsDomainId){
		return rmsDomainMapper.deleteByPrimaryKey(rmsDomainId);
	}
	
	public RmsDomainMapper getRmsDomainMapper() {
		return rmsDomainMapper;
	}

	@Override
	public List<RmsDomain> getRmsDomainsByPage(RmsDomainQueryParam rmsDomainQueryParam) {
		return rmsDomainMapper.selectAllRmsDomains(rmsDomainQueryParam);
	}
	
	@Override
	public int countRmsDomainsByPage(RmsDomainQueryParam rmsDomainQueryParam) {
		
		return rmsDomainMapper.countRmsDomainsByPage(rmsDomainQueryParam);
	}
}
