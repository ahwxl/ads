package com.bplow.netconn.statistics.service.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bplow.netconn.statistics.service.customerReqService;
import com.bplow.netconn.statistics.task.CustomerReqTask;

/**
 * 执行器
 * 将用户请求发送到消息系统
 * @author qian
 *
 */
//@Service
public class CustomerReqServiceImpl implements customerReqService{
	
	private static Logger logger = LoggerFactory.getLogger(CustomerReqServiceImpl.class);
	
	ThreadPoolExecutor executor= null /*(ThreadPoolExecutor)Executors.newFixedThreadPool(2)*/;

	/**
	 * 
	 *
	 */
	@Override
	public boolean executeSendTask(CustomerReqTask task) {
		long starttime = System.currentTimeMillis();
		executor.submit(task);
		//logger.info("发送消息耗时:[{}ms]",System.currentTimeMillis()-starttime);
		return true;
	}

}
