package com.bplow.netconn.systemmng.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.bplow.netconn.systemmng.dao.entity.User;

public interface UserService {
	
	boolean loginAction(User user,HttpServletRequest request);
	
	boolean loginOutAction(User user,HttpServletRequest request);
	/*用户列表分页查询*/
	String queryUserListForPagination(User user)throws SQLException;
	/*用户列表查询*/
	String queryUserList(User user)throws SQLException;
	/*用户查询*/
	String queryUserById(User user)throws SQLException;
	/*添加用户*/
	Integer addUser(User user)throws SQLException;
	/*删除用户*/
	Integer delUser(User user)throws SQLException;
	/*修改用户*/
	Integer modifyUser(User user)throws SQLException;

}
