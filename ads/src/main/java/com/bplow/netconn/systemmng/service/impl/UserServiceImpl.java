package com.bplow.netconn.systemmng.service.impl;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bplow.netconn.systemmng.dao.UserDao;
import com.bplow.netconn.systemmng.dao.entity.User;
import com.bplow.netconn.systemmng.service.UserService;


@Service("sysUserServiceImpl")
public class UserServiceImpl implements UserService{

	@Autowired
	@Qualifier("sysUserDaoImpl")
	private UserDao userDao;
	
	private String sid ="lgu";
	
	@Override
	public boolean loginAction(User user,HttpServletRequest request) {
		Boolean loginResult = false;
		try {
			User returnuser =userDao.queryUserByIdAddPwd(user);
			
			if(null != returnuser){//登陆成功
				request.getSession().setAttribute("urid", returnuser);
				loginResult =true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return loginResult;
	}

}
