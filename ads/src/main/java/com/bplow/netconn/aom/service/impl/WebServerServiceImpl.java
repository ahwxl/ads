/**
 * www.bplow.com
 */
package com.bplow.netconn.aom.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bplow.netconn.aom.dao.TbServerHostDAO;
import com.bplow.netconn.aom.dao.TbWebServerDAO;
import com.bplow.netconn.aom.dao.entity.TbWebServer;
import com.bplow.netconn.aom.dao.entity.TbWebServerExample;
import com.bplow.netconn.aom.service.WebServerService;
import com.bplow.netconn.base.dao.pagination.IPagination;


/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年4月23日 下午4:07:26
 */
@Service
@Transactional
public class WebServerServiceImpl implements WebServerService{
	
	@Autowired
	TbServerHostDAO tbServerHostDAO;
	
	@Autowired
	TbWebServerDAO tbWebServerDao;

	/* (non-Javadoc)
	 * @see com.bplow.netconn.aom.service.WebSeverService#queryWebServerList(com.bplow.netconn.aom.dao.entity.TbWebServer)
	 */
	@Override
	public String queryWebServerList(TbWebServer tbWebServer) throws Exception {
		TbWebServerExample criteria = new TbWebServerExample();
		criteria.createCriteria().andGmtCreateIsNotNull()
				.andGmtModifyIsNotNull();
		IPagination page = tbWebServerDao.queryWebServerForpage(tbWebServer);

		String resultStr = null;
		resultStr = page.getJsonByList();

		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.aom.service.WebSeverService#queryWebServerById(java.lang.String)
	 */
	@Override
	public TbWebServer queryWebServerById(String id) throws SQLException {
		TbWebServer result = this.tbWebServerDao.selectByPrimaryKey(id);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.aom.service.WebSeverService#addWebServer(com.bplow.netconn.aom.dao.entity.TbWebServer)
	 */
	@Override
	public void addWebServer(TbWebServer tbWebServer) throws SQLException {
		tbWebServerDao.insert(tbWebServer);
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.aom.service.WebSeverService#updateWebServer(com.bplow.netconn.aom.dao.entity.TbWebServer)
	 */
	@Override
	public void updateWebServer(TbWebServer tbWebServer) throws SQLException {
		this.tbWebServerDao.updateByPrimaryKey(tbWebServer);
		
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.aom.service.WebSeverService#deleteWebServer(com.bplow.netconn.aom.dao.entity.TbWebServer)
	 */
	@Override
	public void deleteWebServer(TbWebServer tbWebServer) throws SQLException {

		this.tbWebServerDao.deleteByPrimaryKey(tbWebServer.getId());
		
	}

}
