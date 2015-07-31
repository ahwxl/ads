package com.bplow.netconn.query.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bplow.netconn.query.dao.TableDao;
import com.bplow.netconn.query.dao.UserDao;
import com.bplow.netconn.query.dao.entity.User;
import com.bplow.netconn.query.module.LiveAd;
import com.bplow.netconn.query.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TableDao tableDao;
	
	@Autowired
	private CacheManager cacheManager;
	
	
	public List getUserForList(User user) throws SQLException {
		userDao.getUserForList(user);
		return null;
	}

	public String insertUser(User user) throws SQLException {
		userDao.insertUser(user);
		return null;
	}

	public List queryColumns(String tablename) {
		
		List list = tableDao.queryTable(tablename);
		
		Cache cache = cacheManager.getCache("demoCache");
		
		cache.put("", "");
		
		return list;
	}

	public void putAd(String id) {
		LiveAd ad = new LiveAd();
		ad.setId(id);
		Cache cache = cacheManager.getCache("demoCache");
		cache.put(id, ad);
	}

	public void kickIn(String id) {
		Cache cache = cacheManager.getCache("demoCache");
		LiveAd ad = (LiveAd)cache.get(id).get();
		ad.kick();
	}

	public LiveAd queryLiveAd(String id) {
		Cache cache = cacheManager.getCache("demoCache");
		Object ad = cache.get(id).get();
		logger.error(""+((LiveAd)ad).getRequestNum());
		return (LiveAd)ad;
	}
	
	
	
	
	

}
