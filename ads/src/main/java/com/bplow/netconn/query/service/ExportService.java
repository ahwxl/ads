package com.bplow.netconn.query.service;

import javax.jws.WebService;

@WebService(name = "AccountService", targetNamespace = "http://ads.jys-media.com")
public interface ExportService {
	
	/**
	 * 刷新所有缓存
	 */
	void refreshAllCache();

}
