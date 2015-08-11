package com.bplow.netconn.systemmng.dao;

import java.sql.SQLException;

import com.bplow.netconn.systemmng.dao.entity.User;

public interface UserDao {
	
	User queryUserByIdAddPwd(User user)throws SQLException;

}
