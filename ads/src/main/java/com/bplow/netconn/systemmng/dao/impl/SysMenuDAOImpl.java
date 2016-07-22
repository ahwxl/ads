package com.bplow.netconn.systemmng.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bplow.netconn.base.dao.BaseIbatisDaoSupport;
import com.bplow.netconn.base.dao.pagination.IPagination;
import com.bplow.netconn.systemmng.dao.SysMenuDAO;
import com.bplow.netconn.systemmng.dao.entity.SysMenu;
import com.bplow.netconn.systemmng.dao.entity.SysMenuExample;

@Service
public class SysMenuDAOImpl extends BaseIbatisDaoSupport implements SysMenuDAO {

	public int countByExample(SysMenuExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject(
				"sys_menu.countByExample", example);
		return count;
	}

	public int deleteByPrimaryKey(String id) throws SQLException {
		SysMenu _key = new SysMenu();
		_key.setId(id);
		int rows = sqlMapClient.delete("sys_menu.deleteByPrimaryKey", _key);
		return rows;
	}

	public void insert(SysMenu record) throws SQLException {
		sqlMapClient.insert("sys_menu.insert", record);
	}

	@SuppressWarnings("unchecked")
	public List<SysMenu> selectByExample(SysMenuExample example)
			throws SQLException {
		List<SysMenu> list = sqlMapClient.queryForList(
				"sys_menu.selectByExample", example);
		return list;
	}

	public SysMenu selectByPrimaryKey(String id) throws SQLException {
		SysMenu _key = new SysMenu();
		_key.setId(id);
		SysMenu record = (SysMenu) sqlMapClient.queryForObject(
				"sys_menu.selectByPrimaryKey", _key);
		return record;
	}

	public int updateByPrimaryKey(SysMenu record) throws SQLException {
		int rows = sqlMapClient.update("sys_menu.updateByPrimaryKey", record);
		return rows;
	}

	public IPagination selectPage(SysMenu record) throws SQLException {
		IPagination pagination = this.queryForPagenation(
				"sys_menu.countForPage", "sys_menu.selectForPage", record,
				record.getPageNum(), record.getMaxRowNums());
		return pagination;
	}
}