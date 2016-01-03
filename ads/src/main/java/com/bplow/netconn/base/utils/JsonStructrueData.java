package com.bplow.netconn.base.utils;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonStructrueData implements Serializable{
	
	private List data;
	private String exception;
	public PageInfo pageInfo;
	
	public JsonStructrueData(JsonStructureDataHelp obj){
		this.data = obj.data;
	}
	
	public JsonStructrueData(List cntItem,int itemNum){
		this.data = cntItem;
		this.pageInfo = new PageInfo(itemNum);
	}
	public JsonStructrueData(List cntItem,int itemNum,int pageNum){
		this.data = cntItem;
		this.pageInfo = new PageInfo(itemNum,pageNum);
	}
		
	
	public void setData(List cntItem) {
		this.data = cntItem;
	}

	public List getData() {
		return data;
	}
	
	

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public static void main(String[] args)  throws Exception{
		ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        
        List list = new ArrayList();
        
        mapper.writeValue(sw, new JsonStructrueData(list,1));
        
        System.out.println(sw.toString());
	}
	
	class PageInfo{
		private int totalRowNum;//总行数
		//private int pageSize;//每页显示条数
		private int pageNum;//当前显示页码
		//private int totalPageNum;//总页数
		

		public PageInfo(int totalRowNum) {
			super();
			this.totalRowNum = totalRowNum;
		}
		public PageInfo(int totalRowNum,int pageNum) {
			super();
			this.totalRowNum = totalRowNum;
			this.pageNum = pageNum;
		}

		public int getTotalRowNum() {
			return totalRowNum;
		}

		public void setTotalRowNum(int totalRowNum) {
			this.totalRowNum = totalRowNum;
		}

//		public int getPageSize() {
//			return pageSize;
//		}
//
//		public void setPageSize(int pageSize) {
//			this.pageSize = pageSize;
//		}
//
		public int getPageNum() {
			return pageNum;
		}

		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}
//
//		public int getTotalPageNum() {
//			return totalPageNum;
//		}
//
//		public void setTotalPageNum(int totalPageNum) {
//			this.totalPageNum = totalPageNum;
//		}
		
		
	}

}
