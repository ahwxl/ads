package com.bplow.netconn.query.module;

public class LiveAd {
	
	private String id;
	private String name;
	private int    requestNum ;
	
	public void kick(){
		requestNum ++;
	}
	
	public LiveAd() {
		super();
		this.requestNum = 0;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRequestNum() {
		return requestNum;
	}
	public void setRequestNum(int requestNum) {
		this.requestNum = requestNum;
	}
	
	
	

}
