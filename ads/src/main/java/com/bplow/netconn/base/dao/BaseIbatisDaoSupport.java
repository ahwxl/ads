package com.bplow.netconn.base.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bplow.netconn.base.dao.domain.Page;
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
	public  SqlMapClient sqlMapClient;
	
	public IPagination queryForPagenation(String qCounts,String qResults,Page page) throws SQLException{
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
	
	public IPagination queryForPagenation(String qCounts,String qResults,Object param,int pageNum,int maxRowNums) throws SQLException{
		IPagination pagination = new SimplePagination(pageNum, maxRowNums);
		
		/**
		 * 查询总记录数
		 */
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("pageNum", pageNum);
		map.put("maxRowNums", maxRowNums);
		Integer allCountsNum = (Integer)sqlMapClient.queryForObject(qCounts,param);
		if(null != allCountsNum)pagination.setAllCount(allCountsNum);
		
		/**
	     * 数据集
	     */
		List resultList = sqlMapClient.queryForList(qResults,param);
		pagination.setResults(resultList);
		
		pagination.setCurPageIndex(pagination.getCurPageIndex());
	    pagination.setAllPageCount(pagination.getAllPageCount());
		
		return pagination;
	}

}
