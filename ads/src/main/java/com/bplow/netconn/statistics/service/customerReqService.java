package com.bplow.netconn.statistics.service;

import com.bplow.netconn.statistics.task.CustomerReqTask;

public interface customerReqService {

	boolean executeSendTask(CustomerReqTask task);
}
