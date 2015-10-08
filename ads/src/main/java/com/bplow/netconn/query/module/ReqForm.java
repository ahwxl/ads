package com.bplow.netconn.query.module;

public class ReqForm {
	
	private String id;
	private String c;
	private String ext;
	private String cnidx;
	private String refUrl;//媒体地址
	private String sessionId;
	
	public ReqForm() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getCnidx() {
		return cnidx;
	}
	public void setCnidx(String cnidx) {
		this.cnidx = cnidx;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	
	public String getRefUrl() {
		return refUrl;
	}
	public void setRefUrl(String refUrl) {
		this.refUrl = refUrl;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	@Override
	public String toString() {
		return "ReqForm [id=" + id + ", ext=" + ext + ", cnindx=" + cnidx
				+ "]";
	}
	
	

}
