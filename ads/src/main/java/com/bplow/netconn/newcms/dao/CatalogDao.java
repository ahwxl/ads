package com.bplow.netconn.newcms.dao;

import java.util.List;

import com.bplow.netconn.newcms.domain.FmCatalog;

public interface CatalogDao {
	
	/**
	 * 查询单条
	 * @param id
	 * @return
	 */
	public FmCatalog queryCatalogById(String id);
	/**
	 * 查询列表
	 * @param catalog
	 * @return
	 */
	public List queryCatalogList(FmCatalog catalog);
	
	/**
	 * 新增目录
	 * @param catalog
	 * @return
	 */
	public String addCatalog(FmCatalog catalog);
	
	/**
	 * 修改目录
	 * @param catalog
	 * @return
	 */
	public String updateCatalog(FmCatalog catalog);
	/**
	 * 删除目录
	 * @param catalog
	 * @return
	 */
	public String delCatalog(FmCatalog catalog);

}
