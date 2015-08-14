package com.bplow.netconn.query.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.bplow.netconn.query.service.TmplCntCacheService;

@Service
public class ApplicationContextInitService implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private TmplCntCacheService tmplCntCacheService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if("Root WebApplicationContext".equals(event.getApplicationContext().getDisplayName())){
			tmplCntCacheService.init();
		}
		System.out.println("----"+event.getApplicationContext().getDisplayName()+"======");
	}

	


}
