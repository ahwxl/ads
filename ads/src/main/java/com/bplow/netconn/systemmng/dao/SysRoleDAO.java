package com.bplow.netconn.systemmng.dao;

import com.bplow.netconn.base.dao.pagination.IPagination;
import com.bplow.netconn.systemmng.dao.entity.SysRole;
import com.bplow.netconn.systemmng.dao.entity.SysRoleExample;

import java.sql.SQLException;
import java.util.List;

public interface SysRoleDAO {
    int countByExample(SysRoleExample example) throws SQLException;

    int deleteByPrimaryKey(SysRole sysRole) throws SQLException;

    void insert(SysRole record) throws SQLException;

    List<SysRole> selectByExample(SysRoleExample example) throws SQLException;

    SysRole selectByPrimaryKey(String roleId) throws SQLException;

    int updateByPrimaryKey(SysRole record) throws SQLException;

    IPagination selectPage(SysRole record) throws SQLException;
}