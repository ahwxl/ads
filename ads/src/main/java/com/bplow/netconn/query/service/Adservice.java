package com.bplow.netconn.query.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import com.bplow.netconn.query.dao.entity.Ad;
import com.bplow.netconn.query.dao.entity.CustomerData;
import com.bplow.netconn.query.module.ReqForm;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface Adservice {
	
	/**
	 * 获取客户信息
	 * @param id
	 * @return
	 */
	public Ad getAdById(String id);
	
	/**
	 * 获取basejs
	 */
	public String  obtionBaseScript(ReqForm reqForm,String tplPath);
	/**
	 * 获取执行方法，指定要加载客户包装后客户js
	 * @return
	 */
	public String executeMethod(ReqForm req,OutputStream os)throws SQLException;
	/**
	 * 请求包装的js
	 * @return
	 */
	public String requestCustomerJs(String jspath);
	
	public String addAd(Ad ad) throws SQLException;
	
	public List queryAdList(Ad ad) throws SQLException;
	
	public void updateAd(Ad ad) throws SQLException;
	
	public void delAd(Ad ad) throws SQLException;
	
	/**
	 * 批量插入客户数据
	 * @param in
	 * @param customerData
	 * @throws SQLException
	 */
	public void batchAddCustomerData(InputStream in,CustomerData customerData) throws SQLException, IOException;
	
	/**
	 * 查询客户数据 for chart
	 * @param customerData
	 * @return
	 * @throws SQLException
	 */
	public String queryCustomerDataForChar(CustomerData customerData)throws SQLException;
	

	/**
	 * 查询客户数据
	 * @param customerData
	 * @return
	 * @throws SQLException
	 */
	public String queryCustomerData(CustomerData customerData)throws SQLException,JsonProcessingException, UnsupportedEncodingException;
}
