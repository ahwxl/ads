package com.bplow.netconn.query.dao.Impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.bplow.netconn.query.dao.AdDao;
import com.bplow.netconn.query.dao.entity.Ad;
import com.ibatis.sqlmap.client.SqlMapClient;

@Component
@Transactional
public class AdDaoImpl implements AdDao{

	private static Logger logger = LoggerFactory.getLogger(AdDaoImpl.class);
	
	@Autowired
	private SqlMapClient sqlMapClient;
	
	@Override
	public void insertAd(Ad ad) throws SQLException {
		sqlMapClient.insert("insertAd",ad);
	}

	@Override
	public Ad queryAd(int id) throws SQLException {
		logger.info("查询客户by id");
		Map para = new HashMap();
		para.put("id", id);
		Ad ad = (Ad)sqlMapClient.queryForObject("queryAdById", id);
		return ad;
	}
	
	public int delAd(int id) throws SQLException {
		Map para = new HashMap();
		para.put("id", id);
		int ad = sqlMapClient.delete("delAd", id);
		return ad;
	}
	
	public int upateAd(int id) throws SQLException {
		Map para = new HashMap();
		para.put("id", id);
		int tmpid = sqlMapClient.update("queryAdById", id);
		return tmpid;
	}

	@Override
	public List queryAdList(Ad ad) throws SQLException {
		logger.info("查询所有客户。");
		//Assert.isNull(ad, "参数不合法");
		List list = sqlMapClient.queryForList("queryAdForList",ad);
		return list;
	}

}
