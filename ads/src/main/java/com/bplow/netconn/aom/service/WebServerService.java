/**
 * 
 */
package com.bplow.netconn.aom.service;

import java.sql.SQLException;

import com.bplow.netconn.aom.dao.entity.TbWebServer;

/**
 * @desc 上传war包、构建服务
 * @author wangxiaolei
 * @date 2016年4月23日 下午12:41:24
 */
public interface WebServerService {
	
	/**
	 * 分页查询
	 * @param tbWebServer
	 * @return
	 */
	public String queryWebServerList(TbWebServer tbWebServer)throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public TbWebServer queryWebServerById(String id)throws SQLException;
	
	/**
	 * 
	 * @param tbWebServer
	 */
	public void addWebServer(TbWebServer tbWebServer)throws SQLException;
	
	/**
	 * 
	 * @param tbWebServer
	 */
	public void updateWebServer(TbWebServer tbWebServer)throws SQLException;
	
	/**
	 * 
	 * @param tbWebServer
	 */
	public void deleteWebServer(TbWebServer tbWebServer)throws SQLException;
	
}
