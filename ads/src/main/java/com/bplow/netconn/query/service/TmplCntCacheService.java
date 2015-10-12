package com.bplow.netconn.query.service;

public interface TmplCntCacheService {
	
	void init();
	
	public void refreshAll();
	
	public void refreshGroup();
	
	public Object getTmplByKey(String key);
	
	/*缓存请求url内容*/
	public void putQueryStrForCache(String key,String tmpCnt);
	/*根据请求url，获取内容*/
	public String getRenderTmpFromCache(String key);
}
