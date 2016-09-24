package com.jack.rms.model;

import java.util.HashMap;
import java.util.Map;

public class PageParam {

	private Map<String, Object> param = new HashMap<String, Object>();
	
	public PageParam(int page, int pageNums) {
		param.put("page", page);
		param.put("pageNums", pageNums);
	}
	
	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}
	
}
