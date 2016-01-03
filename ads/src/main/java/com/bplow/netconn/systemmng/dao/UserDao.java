package com.bplow.netconn.systemmng.dao;

import java.sql.SQLException;
import java.util.List;

import com.bplow.netconn.base.dao.domain.page;
import com.bplow.netconn.base.dao.pagination.IPagination;
import com.bplow.netconn.systemmng.dao.entity.User;

public interface UserDao {
	
	User queryUserByIdAddPwd(User user)throws SQLException;
	
	User addUser(User user)throws SQLException;
	
	User modifyUser(User user)throws SQLException;
	
	User delUser(User user)throws SQLException;
	
	User queryUserById(User user)throws SQLException;
	
	IPagination queryUserForPagination(User page)throws SQLException;

}
