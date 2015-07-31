package com.bplow.netconn.query.dao.Impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Service;

import com.bplow.netconn.query.dao.UserDao;
import com.bplow.netconn.query.dao.entity.User;
import com.ibatis.sqlmap.client.SqlMapClient;

@Service
public class UserDaoImpl /*extends SqlMapClientDaoSupport*/ implements UserDao{
	
	@Autowired
	private SqlMapClient sqlMapClient;
	

	public List getUserForList(User user) throws SQLException {
		List list = sqlMapClient.queryForList("getUserForList", user);
		return list;
	}

	public String insertUser(User user) throws SQLException {
		sqlMapClient.insert("insertUser", user);
		return "";
	}

	

}
