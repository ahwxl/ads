package com.bplow.netconn.systemmng.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bplow.netconn.systemmng.dao.UserDao;
import com.bplow.netconn.systemmng.dao.entity.User;
import com.ibatis.sqlmap.client.SqlMapClient;

@Component("sysUserDaoImpl")
public class UserDaoImpl implements UserDao{

	@Autowired
	private SqlMapClient sqlMapClient;
	
	@Override
	public User queryUserByIdAddPwd(User _user) throws SQLException {
		Map map =new HashMap();
		map.put("username", _user.getUserName());
		map.put("userpwd", _user.getUserPwd());
		User user = (User)sqlMapClient.queryForObject("queryUserByPwd", map);
		
		return user;
	}

}
