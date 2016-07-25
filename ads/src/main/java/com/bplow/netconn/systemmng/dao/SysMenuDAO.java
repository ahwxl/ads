package com.bplow.netconn.systemmng.dao;

import java.sql.SQLException;
import java.util.List;

import com.bplow.netconn.base.dao.pagination.IPagination;
import com.bplow.netconn.systemmng.dao.entity.SysMenu;
import com.bplow.netconn.systemmng.dao.entity.SysMenuExample;

public interface SysMenuDAO {
    int countByExample(SysMenuExample example) throws SQLException;

    int deleteByPrimaryKey(SysMenu sysMenu) throws SQLException;

    void insert(SysMenu record) throws SQLException;

    List<SysMenu> selectByExample(SysMenuExample example) throws SQLException;

    SysMenu selectByPrimaryKey(SysMenu sysMenu) throws SQLException;

    int updateByPrimaryKey(SysMenu record) throws SQLException;

    IPagination selectPage(SysMenu record) throws SQLException;
}