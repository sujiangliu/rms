package com.jack.rms.model;

import java.util.List;

public class PageResponse {

	private int total;
	
	private int pageNumber;
	
	@SuppressWarnings("rawtypes")
	private List results;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	@SuppressWarnings("rawtypes")
	public List getResults() {
		return results;
	}

	@SuppressWarnings("rawtypes")
	public void setResults(List results) {
		this.results = results;
	}
	
	
}