package com.bplow.netconn.query.module;

public class ReqForm {
	
	private String id;
	private String c;
	private String ext;
	private String cnidx;
	
	
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
	@Override
	public String toString() {
		return "ReqForm [id=" + id + ", ext=" + ext + ", cnindx=" + cnidx
				+ "]";
	}
	
	

}
