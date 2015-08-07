package com.bplow.netconn.query.dao.entity;

import java.util.Date;

public class CustomerData implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -908213410685326754L;
	
	private Integer id;
	private String  customerId;
	private String  customerName;
	private String  adAddr;
	private Long    showNum;
	private Long    clickNum;
	private Long    income;
	private Date    uploadData;
	private Date    gmtCreate;
	private Date    gmtModify;
	
	
	public CustomerData() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAdAddr() {
		return adAddr;
	}
	public void setAdAddr(String adAddr) {
		this.adAddr = adAddr;
	}
	public Long getShowNum() {
		return showNum;
	}
	public void setShowNum(Long showNum) {
		this.showNum = showNum;
	}
	public Long getClickNum() {
		return clickNum;
	}
	public void setClickNum(Long clickNum) {
		this.clickNum = clickNum;
	}
	public Long getIncome() {
		return income;
	}
	public void setIncome(Long income) {
		this.income = income;
	}
	public Date getUploadData() {
		return uploadData;
	}
	public void setUploadData(Date uploadData) {
		this.uploadData = uploadData;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModify() {
		return gmtModify;
	}
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	
	
	
}
