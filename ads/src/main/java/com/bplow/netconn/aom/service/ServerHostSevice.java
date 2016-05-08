/**
 * 
 */
package com.bplow.netconn.aom.service;

import java.sql.SQLException;

import com.bplow.netconn.aom.dao.entity.TbServerHost;

/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年4月23日 下午12:40:51
 */
public interface ServerHostSevice {
	
	/**
	 * 主机列表查询
	 * @param serverHost
	 * @return
	 */
	public String queryServerHostList(TbServerHost serverHost)throws SQLException;
	
	/**
	 * 查询主机
	 * @param hostId
	 * @return
	 */
	public TbServerHost queryServerHostById(String hostId)throws SQLException;
	
	/**
	 * 添加
	 * @param serverHost
	 */
	public void addServerHost(TbServerHost serverHost)throws SQLException;
	
	/**
	 * 修改
	 * @param serverHost
	 */
	public void updateServerHost(TbServerHost serverHost)throws SQLException;
	
	/**
	 * 删除
	 * @param serverHost
	 */
	public void deleteServerHost(TbServerHost serverHost)throws SQLException;
	

}
