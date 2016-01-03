package com.bplow.netconn.systemmng.dao.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bplow.netconn.base.dao.BaseIbatisDaoSupport;
import com.bplow.netconn.base.dao.domain.page;
import com.bplow.netconn.base.dao.pagination.IPagination;
import com.bplow.netconn.systemmng.dao.UserDao;
import com.bplow.netconn.systemmng.dao.entity.User;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ibatis.sqlmap.client.SqlMapClient;

@Component("sysUserDaoImpl")
public class UserDaoImpl extends BaseIbatisDaoSupport implements UserDao{

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

	@Override
	public User addUser(User user) throws SQLException {
		sqlMapClient.insert("insertUser", user);
		return null;
	}

	@Override
	public User delUser(User user) throws SQLException {
		Integer id = user.getId();
		sqlMapClient.delete("delUserById", id);
		return null;
	}

	@Override
	public User queryUserById(User user) throws SQLException {
		
		return null;
	}

	@Override
	public User modifyUser(User user) throws SQLException {
		sqlMapClient.update("updateUser",user);
		return null;
	}

	/**
	 * 分页查询
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public IPagination queryUserForPagination(User page) throws SQLException {
		IPagination pagination = this.queryForPagenation("selectCountsForUser", "getUserForList", page);
		String dataToString = null;
		try {
			dataToString = pagination.getJsonByList();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pagination;
	}

}
