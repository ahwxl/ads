package com.bplow.netconn.base.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bplow.netconn.base.dao.domain.page;
import com.bplow.netconn.base.dao.pagination.IPagination;
import com.bplow.netconn.base.dao.pagination.SimplePagination;
import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * 
 * @desc ibatis分页查询
 * @author wangxiaolei
 * @date 2015年12月19日 下午7:04:14
 */

public class BaseIbatisDaoSupport {
	
	@Autowired
	private SqlMapClient sqlMapClient;
	
	public IPagination queryForPagenation(String qCounts,String qResults,page page) throws SQLException{
		IPagination pagination = new SimplePagination(page.getPageNum(), page.getMaxRowNums());
		
		/**
		 * 查询总记录数
		 */
		Integer allCountsNum = (Integer)sqlMapClient.queryForObject(qCounts,page);
		if(null != allCountsNum)pagination.setAllCount(allCountsNum);
		
		/**
	     * 数据集
	     */
		List resultList = sqlMapClient.queryForList(qResults,page);
		pagination.setResults(resultList);
		
		pagination.setCurPageIndex(pagination.getCurPageIndex());
	    pagination.setAllPageCount(pagination.getAllPageCount());
		
		return pagination;
	}

}
