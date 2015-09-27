package com.bplow.netconn.query.service;

import javax.jws.WebService;

@WebService(name = "AccountService", targetNamespace = "http://ads.jys-media.com")
public interface ExportService {
	
	/**
	 * 刷新所有缓存
	 */
	void refreshAllCache();
	/**
	 * 切换日志记录开关
	 */
	void switchEnableLogFlag();
	
	/**
	 * 设置日志最大记录条数
	 */
	void setMaxLogNum(int logNum);
	
	/**
	 * 
	 */
	void addBlackName(String blackName);
	
	void switchSendMsgLog();

}
