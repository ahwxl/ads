package com.bplow.netconn.statistics.service;

import com.bplow.netconn.statistics.domain.CustomerReq;

public interface SendReqService {
	
	void sendMessage(CustomerReq req);

}
