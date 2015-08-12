package com.bplow.netconn.query.dao.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CustomerData implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -908213410685326754L;
	
	private Integer id;
	private String customerId;
	private String customerName;
	private String adAddr;
	private Double showNum;
	private Double clickNum;
	private Double income;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date uploadData;
	private Date gmtCreate;
	private Date gmtModify;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	
	//扩展字段
	private String organizeId;
	private String loginUserId;
	
	
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

	public Double getShowNum() {
		return showNum;
	}

	public void setShowNum(Double showNum) {
		this.showNum = showNum;
	}

	public Double getClickNum() {
		return clickNum;
	}

	public void setClickNum(Double clickNum) {
		this.clickNum = clickNum;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getOrganizeId() {
		return organizeId;
	}

	public void setOrganizeId(String organizeId) {
		this.organizeId = organizeId;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	
	
	
}
