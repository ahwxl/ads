package com.bplow.netconn.systemmng.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.bplow.netconn.systemmng.dao.entity.SysUser;

public interface UserService {
	
	boolean loginAction(SysUser user,HttpServletRequest request);
	
	boolean loginOutAction(SysUser user,HttpServletRequest request);
	/*用户列表分页查询*/
	String queryUserListForPagination(SysUser user)throws SQLException;
	/*用户列表查询*/
	String queryUserList(SysUser user)throws SQLException;
	/*用户查询*/
	String queryUserById(SysUser user)throws SQLException;
	/*添加用户*/
	Integer addUser(SysUser user)throws SQLException;
	/*删除用户*/
	Integer delUser(SysUser user)throws SQLException;
	/*修改用户*/
	Integer modifyUser(SysUser user)throws SQLException;

}
