package com.jack.rms.model.request;

public class HouseQueryParam {
	
	private String hName;
	
	private String hAddress;
	
	private String hContent;
	
	private Integer hBedroomNum;
	
	private Integer pageNumber;
	
	private Integer pageSize;
	
	private Integer startNumber;

	public String gethName() {
		return hName;
	}

	public void sethName(String hName) {
		this.hName = hName;
	}

	public String gethAddress() {
		return hAddress;
	}

	public void sethAddress(String hAddress) {
		this.hAddress = hAddress;
	}

	public String gethContent() {
		return hContent;
	}

	public void sethContent(String hContent) {
		this.hContent = hContent;
	}

	public Integer gethBedroomNum() {
		return hBedroomNum;
	}

	public void sethBedroomNum(Integer hBedroomNum) {
		this.hBedroomNum = hBedroomNum;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(Integer startNumber) {
		this.startNumber = startNumber;
	}
}
