/**
 * www.bplow.com
 */
package com.bplow.netconn.aom.service.impl;

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
import com.bplow.netconn.base.json.JsonHelper;
import com.fasterxml.jackson.core.JsonProcessingException;


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
	public String queryWebServerList(TbWebServer tbWebServer) throws SQLException {
		TbWebServerExample criteria = new TbWebServerExample();
		criteria.createCriteria().andGmtCreateIsNotNull().andGmtModifyIsNotNull();
		List<TbWebServer> list = tbWebServerDao.selectByExample(criteria);
		
		String resultStr = null;
		try {
			resultStr = JsonHelper.convertToStr(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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
