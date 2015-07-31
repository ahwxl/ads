package com.bplow.netconn.query.service;

public interface TmplCntCacheService {
	
	void init();
	
	public void refreshAll();
	
	public void refreshGroup();
	
	public Object getTmplByKey(String key);

}
