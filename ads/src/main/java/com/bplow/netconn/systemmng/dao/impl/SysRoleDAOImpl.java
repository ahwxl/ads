package com.bplow.netconn.systemmng.dao.impl;

import com.bplow.netconn.base.dao.BaseIbatisDaoSupport;
import com.bplow.netconn.base.dao.pagination.IPagination;
import com.bplow.netconn.systemmng.dao.SysRoleDAO;
import com.bplow.netconn.systemmng.dao.entity.SysRole;
import com.bplow.netconn.systemmng.dao.entity.SysRoleExample;
import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SysRoleDAOImpl extends BaseIbatisDaoSupport implements SysRoleDAO {

    public int countByExample(SysRoleExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("sys_role.countByExample", example);
        return count;
    }

    public int deleteByPrimaryKey(String roleId) throws SQLException {
        SysRole _key = new SysRole();
        _key.setRoleId(roleId);
        int rows = sqlMapClient.delete("sys_role.deleteByPrimaryKey", _key);
        return rows;
    }

    public void insert(SysRole record) throws SQLException {
        sqlMapClient.insert("sys_role.insert", record);
    }

    @SuppressWarnings("unchecked")
    public List<SysRole> selectByExample(SysRoleExample example) throws SQLException {
        List<SysRole> list = sqlMapClient.queryForList("sys_role.selectByExample", example);
        return list;
    }

    public SysRole selectByPrimaryKey(String roleId) throws SQLException {
        SysRole _key = new SysRole();
        _key.setRoleId(roleId);
        SysRole record = (SysRole) sqlMapClient.queryForObject("sys_role.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByPrimaryKey(SysRole record) throws SQLException {
        int rows = sqlMapClient.update("sys_role.updateByPrimaryKey", record);
        return rows;
    }

    public IPagination selectPage(SysRole record) throws SQLException {
        IPagination pagination =  this.queryForPagenation("sys_role.countForPage","sys_role.selectForPage",record);
        return pagination;
    }
}