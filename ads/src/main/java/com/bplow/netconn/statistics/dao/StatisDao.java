package com.bplow.netconn.statistics.dao;

import java.util.List;

import com.bplow.netconn.statistics.domain.CustomerReq;

public interface StatisDao {

	void lazyInsertReqMsg(List<CustomerReq> listData);
}
