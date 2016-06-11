package com.bplow.netconn.systemmng.dao;

import java.sql.SQLException;
import java.util.List;

import com.bplow.netconn.base.dao.domain.Page;
import com.bplow.netconn.base.dao.pagination.IPagination;
import com.bplow.netconn.systemmng.dao.entity.SysUser;

public interface UserDao {
	
	SysUser queryUserByIdAddPwd(SysUser user)throws SQLException;
	
	SysUser addUser(SysUser user)throws SQLException;
	
	SysUser modifyUser(SysUser user)throws SQLException;
	
	SysUser delUser(SysUser user)throws SQLException;
	
	SysUser queryUserById(SysUser user)throws SQLException;
	
	IPagination queryUserForPagination(SysUser page)throws SQLException;

}
