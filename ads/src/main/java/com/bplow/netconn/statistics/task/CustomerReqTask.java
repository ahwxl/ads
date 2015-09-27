package com.bplow.netconn.statistics.task;

import java.util.concurrent.Callable;

import com.bplow.netconn.statistics.domain.CustomerReq;
import com.bplow.netconn.statistics.service.SendReqService;

/**
 * 发送请求到消息系统
 * @author qian
 *
 */
public class CustomerReqTask implements Callable<Integer>{

	private CustomerReq req;
	
	private SendReqService sendReqService;
	
	
	public CustomerReqTask(CustomerReq req, SendReqService sendReqService) {
		super();
		this.req = req;
		this.sendReqService = sendReqService;
	}



	@Override
	public Integer call() throws Exception {
		
		sendReqService.sendMessage(req);
		
		return null;
	}
	
	

}
