package com.jack.rms.service;

import java.util.List;

import com.jack.rms.dao.RmsDomainMapper;
import com.jack.rms.model.RmsDomain;
import com.jack.rms.model.request.RmsDomainQueryParam;

public interface IRmsDomainService {

	public RmsDomain getRmsDomainById(int id);
	
	public int saveRmsDomain(RmsDomain rmsDomain);
	
	public int updateRmsDomain(RmsDomain rmsDomain);
	
	public int delRmsDomain(Integer rmsDomainId);
	
	public List<RmsDomain> getRmsDomainsByPage(RmsDomainQueryParam rmsDomainQueryParam);
	
	public int countRmsDomainsByPage(RmsDomainQueryParam rmsDomainQueryParam);
}
