/**
 * www.bplow.com
 */
package com.bplow.netconn.aom.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bplow.netconn.aom.dao.TbServerHostDAO;
import com.bplow.netconn.aom.dao.TbWebServerDAO;
import com.bplow.netconn.aom.dao.entity.TbWebServer;
import com.bplow.netconn.aom.dao.entity.TbWebServerExample;
import com.bplow.netconn.aom.service.WebSeverService;
import com.bplow.netconn.base.dao.pagination.IPagination;
import com.bplow.netconn.base.json.JsonHelper;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年4月23日 下午4:07:26
 */
@Service
public class WebServerServiceImpl implements WebSeverService{
	
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
	public TbWebServer queryWebServerById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.aom.service.WebSeverService#addWebServer(com.bplow.netconn.aom.dao.entity.TbWebServer)
	 */
	@Override
	public void addWebServer(TbWebServer tbWebServer) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.aom.service.WebSeverService#updateWebServer(com.bplow.netconn.aom.dao.entity.TbWebServer)
	 */
	@Override
	public void updateWebServer(TbWebServer tbWebServer) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.aom.service.WebSeverService#deleteWebServer(com.bplow.netconn.aom.dao.entity.TbWebServer)
	 */
	@Override
	public void deleteWebServer(TbWebServer tbWebServer) {
		// TODO Auto-generated method stub
		
	}

}
