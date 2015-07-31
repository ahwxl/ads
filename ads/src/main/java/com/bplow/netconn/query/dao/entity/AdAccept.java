package com.bplow.netconn.query.dao.entity;

public class AdAccept {
	
	private int    id;
	private String adName;
	private String adAddr;
	private String adUrl;
	
	
	public AdAccept() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getAdAddr() {
		return adAddr;
	}
	public void setAdAddr(String adAddr) {
		this.adAddr = adAddr;
	}
	public String getAdUrl() {
		return adUrl;
	}
	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}
	
	
	

}
