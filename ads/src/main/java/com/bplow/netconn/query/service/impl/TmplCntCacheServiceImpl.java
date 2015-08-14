package com.bplow.netconn.query.service.impl;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.bplow.netconn.base.utils.TraceNoGenerater;
import com.bplow.netconn.query.dao.entity.Ad;
import com.bplow.netconn.query.service.Adservice;
import com.bplow.netconn.query.service.TmplCntCacheService;

@Service("tmplCntCacheService")
public class TmplCntCacheServiceImpl implements TmplCntCacheService,InitializingBean{

	private static Logger logger = LoggerFactory.getLogger(TmplCntCacheServiceImpl.class);
	
	@Autowired
	CacheManager cacheMananger;
	
	@Autowired
	Adservice adService;
	
	
	Cache cache = null;
	
	private final Lock queueLock = new ReentrantLock();
	
	private final String [] tmplarray = new String[]{"/js/base.min.js",
			"/js/adcommp.min.js"
	};
	
	public void init(){
        cache = cacheMananger.getCache("tmplCache");
		refreshAll();
		logger.info("缓存初始化完成");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		/*cache = cacheMananger.getCache("tmplCache");
		
		refreshAll();
		logger.info("缓存初始化");*/
	}

	@Override
	public void refreshAll() {
		logger.info("refresh tmplate cache begin:");
		queueLock.lock();
		try {
			/**
			 * 刷新模板缓存
			 */
			for(String tmplpath : tmplarray){
				String tmplcnt = obtainTmplCnt(tmplpath);
				cache.put(tmplpath,tmplcnt);
			}
			/**
			 * 刷新客户缓存
			 */
			List<Ad> list = adService.queryAdList(new Ad());
			for(Ad ad : list){
				cache.put(ad.getId()+"",ad);
				String wrapjspath = "/js/"+ad.getAdId()+".js";
				String wrapjstmpl = obtainTmplCnt(wrapjspath);
				cache.put(wrapjspath,wrapjstmpl);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("refresh cache error:{}",e);
		}finally{
			queueLock.unlock();
		}
		logger.info("refresh tmplate cache end:");
	}

	@Override
	public void refreshGroup() {
		
	}

	@Override
	public Object getTmplByKey(String key) {
		
		Object tmplcnt = null;
		Cache.ValueWrapper tmplcntobj = cache.get(key);
		if(null != tmplcntobj ){
			tmplcnt = tmplcntobj.get();
		}
		
		if(tmplcnt == null){
			logger.info("cache中没有key[{}]value[{}]",key,tmplcnt);
			refreshAll();
		}
		return tmplcnt;
	}
	
	public String obtainTmplCnt(String path){
		String cnt = null;
		InputStream in = this.getClass().getResourceAsStream(path);
		try {
			cnt = IOUtils.toString(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
