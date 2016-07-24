package com.bplow.netconn.base.dao.domain;


public class PageImpl implements Page{
	
	private int pageNum;
	private int maxRowNums;
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setMaxRowNums(int maxRowNums) {
		this.maxRowNums = maxRowNums;
	}

	@Override
	public int getPageNum() {
		return this.pageNum;
	}

	@Override
	public int getMaxRowNums() {
		return this.maxRowNums;
	}

}
