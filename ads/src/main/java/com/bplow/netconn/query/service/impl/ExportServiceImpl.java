package com.bplow.netconn.query.service.impl;

import javax.jws.WebService;

import org.apache.cxf.feature.Features;

import com.bplow.netconn.query.service.ExportService;
import com.bplow.netconn.query.service.LogConfigService;
import com.bplow.netconn.query.service.TmplCntCacheService;


@WebService(serviceName = "AccountService", endpointInterface = "com.bplow.netconn.query.service.ExportService", targetNamespace = "http://ads.jys-media.com")
//增加inbound/outbound SOAP内容的日志
@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class ExportServiceImpl implements ExportService{
	
	private TmplCntCacheService tmplCntCacheService;
	
	private LogConfigService logConfigService;

	@Override
	public void refreshAllCache() {
		tmplCntCacheService.init();
	}
	
	@Override
	public void switchEnableLogFlag() {
		logConfigService.switchOpenLog();
		
	}
	
	@Override
	public void addBlackName(String blackName) {
		logConfigService.addBlackName(blackName);
	}


	@Override
	public void setMaxLogNum(int logNum) {
		logConfigService.setMaxErrorLogNum(logNum);
	}

	public void setTmplCntCacheService(TmplCntCacheService tmplCntCacheService) {
		this.tmplCntCacheService = tmplCntCacheService;
	}

	public void setLogConfigService(LogConfigService logConfigService) {
		this.logConfigService = logConfigService;
	}

	@Override
	public void switchSendMsgLog() {
		logConfigService.switchSendMsgLog();
	}





}
