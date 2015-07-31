package com.bplow.netconn.query.dao.entity;

import java.util.List;

/**
 * 
 * @author wb-wangxiaolei.xl
 * @version $Id: Table.java, v 0.1 2015年4月29日 下午3:46:58 wb-wangxiaolei.xl Exp $
 */
public class Table {

	private String name;
	private String desc;
	private List columns;
	
	
	public Table() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List getColumns() {
		return columns;
	}

	public void setColumns(List columns) {
		this.columns = columns;
	}

	
	
	
}
