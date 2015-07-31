package com.bplow.netconn.newcms.dao;

import java.util.List;

import com.bplow.netconn.newcms.domain.FmProduct;

public interface ProductDao {
	
	/**
	 * 查询产品
	 * @param id
	 * @return
	 */
	public FmProduct queryById(String id);
	
	/**
	 * 查询产品列表
	 * @param product
	 * @return
	 */
	public List<FmProduct> queryList(FmProduct product);


}
