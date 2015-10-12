package com.bplow.netconn.statistics.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.bplow.netconn.statistics.dao.StatisDao;
import com.bplow.netconn.statistics.domain.CustomerReq;
import com.bplow.netconn.statistics.domain.EveryDayAdReqNumStat;
import com.bplow.netconn.statistics.service.StatisDayService;

/**
 * 统计每天 媒体 广告位 在不同页面的展示 次数
 * @author qian
 *
 */
//@Service("statisDayService")
public class StatisDayServiceImpl implements StatisDayService{
	
	private static Logger logger = LoggerFactory.getLogger("media-digest");
	
	@Autowired
	private StatisDao statisDao;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	@Value("${containerSize}")
	private final Integer containerSize=3;
	
	public Map<String, EveryDayAdReqNumStat> statisMediaAdNumMap =Collections.synchronizedMap( new Hashtable<String, EveryDayAdReqNumStat>(30));

	private List<CustomerReq> reqDataList = new ArrayList<CustomerReq>(containerSize);
	
	private final Lock queueLock = new ReentrantLock();
	
	
	@Override
	public boolean statictis(CustomerReq req) {
		//logger.info("VIEW:[{},{},{},{}]",req.getMediaId(),req.getAdNo(),req.getRefUrl(),req.getSessionId());
		boolean result = true;
		String key = req.getMediaId()+req.getAdNo()+req.getRefUrl();
		EveryDayAdReqNumStat everDayAd = statisMediaAdNumMap.get(key);
		if(null == everDayAd){
			EveryDayAdReqNumStat everDayAdTmp = new EveryDayAdReqNumStat();
			statisMediaAdNumMap.put(key, everDayAdTmp);
			everDayAdTmp.incrementNum();
		}else{
			everDayAd.incrementNum();
		}
		queueLock.lock();
		try {
		reqDataList.add(req);
		
		if(reqDataList.size() == containerSize){
			this.transactionTemplate.execute(new TransactionCallback<Object>() {
				public Object doInTransaction(TransactionStatus status) {
					try {
						statisDao.lazyInsertReqMsg(reqDataList);
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
					return true;
				}
			});
			reqDataList.clear();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			queueLock.unlock();
		}
		
		
		
		return result;
	}

}
