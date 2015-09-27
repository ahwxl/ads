package com.bplow.netconn.statistics.domain;

public class CustomerReq implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5024457767789480855L;
	
	private long   id;
	private String mediaId;//媒体编号
	private String adNo;  //广告编号
	private String reqUrl;//请求url
	private String refUrl;//媒体页面地址
	private String extNo; //回流号
	private String sessionId;//会话编号
	
	public CustomerReq(String mediaId, String adNo, String reqUrl,
			String refUrl, String extNo, String sessionId) {
		super();
		this.mediaId = mediaId;
		this.adNo = adNo;
		this.reqUrl = reqUrl;
		this.refUrl = refUrl;
		this.extNo = extNo;
		this.sessionId = sessionId;
	}

	public CustomerReq() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getAdNo() {
		return adNo;
	}

	public void setAdNo(String adNo) {
		this.adNo = adNo;
	}

	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}

	public String getRefUrl() {
		return refUrl;
	}

	public void setRefUrl(String refUrl) {
		this.refUrl = refUrl;
	}

	public String getExtNo() {
		return extNo;
	}

	public void setExtNo(String extNo) {
		this.extNo = extNo;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	

}
