package com.bplow.netconn.query.dao.entity;

/**
 * 
 * @author wb-wangxiaolei.xl
 * @version $Id: Columns.java, v 0.1 2015年4月29日 下午3:46:49 wb-wangxiaolei.xl Exp $
 */
public class Columns {
	
	private String name;
	private String dstype;
	private String jtype;
	private String desc;
	private int order;
	
	
	public Columns() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDstype() {
		return dstype;
	}
	public void setDstype(String dstype) {
		this.dstype = dstype;
	}
	public String getJtype() {
		return jtype;
	}
	public void setJtype(String jtype) {
		this.jtype = jtype;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	

}
