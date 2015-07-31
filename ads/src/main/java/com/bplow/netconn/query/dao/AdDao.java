package com.bplow.netconn.query.dao;

import java.sql.SQLException;
import java.util.List;


/**
 * 
 */
import com.bplow.netconn.query.dao.entity.Ad;

public interface AdDao {
	
	public void insertAd(Ad ad)throws SQLException;
	
	public Ad queryAd(int id)throws SQLException;
	
	public List queryAdList(Ad ad)throws SQLException;
	
	public int delAd(int id) throws SQLException;
	
	public int upateAd(int id) throws SQLException;

}
