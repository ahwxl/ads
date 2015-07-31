package com.bplow.netconn.query.service;

import java.sql.SQLException;
import java.util.List;

import com.bplow.netconn.query.dao.entity.User;
import com.bplow.netconn.query.module.LiveAd;

public interface UserService {
	
	public List getUserForList(User user)throws SQLException;
	
	public String insertUser(User user)throws SQLException;
	
	
	public List queryColumns(String tablename);
	
	
	public void putAd(String id);
	
	public void kickIn(String id);
	
	public LiveAd queryLiveAd(String id);
	

}
