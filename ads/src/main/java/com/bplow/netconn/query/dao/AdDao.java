package com.bplow.netconn.query.dao;

import java.sql.SQLException;
import java.util.List;



/**
 * 
 */
import com.bplow.netconn.query.dao.entity.Ad;
import com.bplow.netconn.query.dao.entity.CustomerData;

public interface AdDao {
	
	public void insertAd(Ad ad)throws SQLException;
	
	public Ad queryAd(int id)throws SQLException;
	
	public List queryAdList(Ad ad)throws SQLException;
	
	public int delAd(int id) throws SQLException;
	
	public int upateAd(int id) throws SQLException;
	
	/**
	 * 批量导入客户信息
	 * @param data
	 * @throws SQLException
	 */
	public void batchInsertCustomerData(List <CustomerData> data) throws SQLException;
	/**
	 * 查询客户数据
	 * @param customer
	 * @return
	 * @throws SQLException
	 */
	public List queryCustomerData(CustomerData customer)throws SQLException;

}
