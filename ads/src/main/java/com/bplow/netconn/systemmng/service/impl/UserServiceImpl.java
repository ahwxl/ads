package com.bplow.netconn.systemmng.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bplow.netconn.base.dao.pagination.IPagination;
import com.bplow.netconn.base.json.JsonHelper;
import com.bplow.netconn.systemmng.dao.UserDao;
import com.bplow.netconn.systemmng.dao.entity.SysUser;
import com.bplow.netconn.systemmng.service.UserService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


@Service("sysUserServiceImpl")
public class UserServiceImpl implements UserService{
	
	private static Logger log    =  LoggerFactory.getLogger("common-digest");

	@Autowired
	@Qualifier("sysUserDaoImpl")
	private UserDao userDao;
	
	private String sid ="lgu";
	
	@Override
	public boolean loginAction(SysUser user,HttpServletRequest request) {
		Boolean loginResult = false;
		try {
			SysUser returnuser =userDao.queryUserByIdAddPwd(user);
			
			if(null != returnuser){//登陆成功
				request.getSession().setAttribute(sid, returnuser);
				loginResult =true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return loginResult;
	}

	@Override
	public boolean loginOutAction(SysUser user, HttpServletRequest request) {
		request.getSession().removeAttribute(sid);
		return true;
	}

	/**
	 * 用户列表分页查询
	 */
	public String queryUserListForPagination(SysUser user) throws SQLException {
		IPagination page = userDao.queryUserForPagination(user);
		Map map = new HashMap();
		map.put("dataroot", page.getResults());
		String strobj = null;
		try {
			strobj = JsonHelper.convertToStr(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return strobj;
	}
	
	/**
	 * 查询用户信息
	 */
	public String queryUserById(SysUser user) throws SQLException {
		SysUser userTmp = userDao.queryUserById(user);
		String strobj = null;
		try {
			strobj = JsonHelper.convertToStr(userTmp);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return strobj;
	}

	/**
	 * 查询用户
	 * @throws SQLException 
	 */
	public String queryUserList(SysUser user) throws SQLException {
		
		IPagination pagination = userDao.queryUserForPagination(user);
		String str = null;
		try {
			str = pagination.getJsonByList();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info(str);
		return str;
	}

	/**
	 * 添加用户
	 * @throws SQLException 
	 */
	public Integer addUser(SysUser user) throws SQLException {
		userDao.addUser(user);
		return null;
	}

	/**
	 * 删除用户
	 * @throws SQLException 
	 */
	public Integer delUser(SysUser user) throws SQLException {
		userDao.delUser(user);
		return null;
	}

	/**
	 * 修改用户
	 * @throws SQLException 
	 */
	public Integer modifyUser(SysUser user) throws SQLException {
		userDao.modifyUser(user);
		return null;
	}
	
	

}
