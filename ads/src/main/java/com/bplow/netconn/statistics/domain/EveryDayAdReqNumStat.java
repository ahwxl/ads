package com.bplow.netconn.statistics.domain;

public class EveryDayAdReqNumStat implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1872738432704908359L;
	private String currentTime;
	private String mediaId;//媒体编号
	private String adNo;   //广告编号
	private String refUrl; //媒体页面
	private Integer reqnum;//请求次数
	
	public void incrementNum(){
		reqnum++;
	}
	
	public EveryDayAdReqNumStat() {
		super();
		this.reqnum = 0;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
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

	public String getRefUrl() {
		return refUrl;
	}

	public void setRefUrl(String refUrl) {
		this.refUrl = refUrl;
	}

	public Integer getReqnum() {
		return reqnum;
	}

	public void setReqnum(Integer reqnum) {
		this.reqnum = reqnum;
	}
	
	

}
