package com.bplow.netconn.query.service.impl;

import javax.jws.WebService;

import org.apache.cxf.feature.Features;

import com.bplow.netconn.query.service.ExportService;
import com.bplow.netconn.query.service.TmplCntCacheService;


@WebService(serviceName = "AccountService", endpointInterface = "com.bplow.netconn.query.service.ExportService", targetNamespace = "http://ads.jys-media.com")
//增加inbound/outbound SOAP内容的日志
@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class ExportServiceImpl implements ExportService{
	
	private TmplCntCacheService tmplCntCacheService;

	@Override
	public void refreshAllCache() {
		tmplCntCacheService.init();
	}

	public void setTmplCntCacheService(TmplCntCacheService tmplCntCacheService) {
		this.tmplCntCacheService = tmplCntCacheService;
	}
	
	
	

}
