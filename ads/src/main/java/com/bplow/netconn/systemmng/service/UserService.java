package com.bplow.netconn.systemmng.service;

import javax.servlet.http.HttpServletRequest;

import com.bplow.netconn.systemmng.dao.entity.User;

public interface UserService {
	
	boolean loginAction(User user,HttpServletRequest request);

}
