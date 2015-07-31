package com.bplow.netconn.query.dao;

import java.sql.SQLException;
import java.util.List;

import com.bplow.netconn.query.dao.entity.User;

public interface UserDao {
	
	public List getUserForList(User user)throws SQLException;
	
	public String insertUser(User user)throws SQLException;

}
