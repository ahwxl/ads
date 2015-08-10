package com.bplow.netconn.newcms.dao;

// default package
// Generated 2015-7-12 12:19:33 by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * FmCatalog generated by hbm2java
 */
public class FmCatalog implements java.io.Serializable {

	private String catalogId;
	private String catalogName;
	private String catalogDesc;
	private String secondName;
	private String imageSrc;
	private Integer orderId;
	private Date operateDate;
	private Character isDeleteFlag;
	private String catalogType;
	private String parentCatalogId;

	public FmCatalog() {
	}

	public FmCatalog(String catalogId) {
		this.catalogId = catalogId;
	}

	public FmCatalog(String catalogId, String catalogName, String catalogDesc,
			String secondName, String imageSrc, Integer orderId,
			Date operateDate, Character isDeleteFlag, String catalogType,
			String parentCatalogId) {
		this.catalogId = catalogId;
		this.catalogName = catalogName;
		this.catalogDesc = catalogDesc;
		this.secondName = secondName;
		this.imageSrc = imageSrc;
		this.orderId = orderId;
		this.operateDate = operateDate;
		this.isDeleteFlag = isDeleteFlag;
		this.catalogType = catalogType;
		this.parentCatalogId = parentCatalogId;
	}

	public String getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalogName() {
		return this.catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getCatalogDesc() {
		return this.catalogDesc;
	}

	public void setCatalogDesc(String catalogDesc) {
		this.catalogDesc = catalogDesc;
	}

	public String getSecondName() {
		return this.secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getImageSrc() {
		return this.imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public Character getIsDeleteFlag() {
		return this.isDeleteFlag;
	}

	public void setIsDeleteFlag(Character isDeleteFlag) {
		this.isDeleteFlag = isDeleteFlag;
	}

	public String getCatalogType() {
		return this.catalogType;
	}

	public void setCatalogType(String catalogType) {
		this.catalogType = catalogType;
	}

	public String getParentCatalogId() {
		return this.parentCatalogId;
	}

	public void setParentCatalogId(String parentCatalogId) {
		this.parentCatalogId = parentCatalogId;
	}

}