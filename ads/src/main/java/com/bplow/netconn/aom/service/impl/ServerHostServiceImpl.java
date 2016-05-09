/**
 * www.bplow.com
 */
package com.bplow.netconn.aom.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bplow.netconn.aom.dao.TbServerHostDAO;
import com.bplow.netconn.aom.dao.entity.TbServerHost;
import com.bplow.netconn.aom.dao.entity.TbServerHostExample;
import com.bplow.netconn.aom.service.ServerHostSevice;
import com.bplow.netconn.base.json.JsonHelper;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @desc
 * @author wangxiaolei
 * @date 2016年4月23日 下午4:06:25
 */
@Service
public class ServerHostServiceImpl implements ServerHostSevice {

	@Autowired
	private TbServerHostDAO tbServerHostDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bplow.netconn.aom.service.ServerHostSevice#queryServerHostList(com
	 * .bplow.netconn.aom.dao.entity.TbServerHost)
	 */
	@Override
	public String queryServerHostList(TbServerHost serverHost)
			throws SQLException {

		TbServerHostExample example = new TbServerHostExample();
		List list = tbServerHostDAO.selectByExample(example);

		Map map = new HashMap();
		map.put("dataroot", list);
		
		String serverHostList = null;
		try {
			serverHostList = JsonHelper.convertToStr(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return serverHostList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bplow.netconn.aom.service.ServerHostSevice#queryServerHostById(java
	 * .lang.String)
	 */
	@Override
	public TbServerHost queryServerHostById(String hostId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bplow.netconn.aom.service.ServerHostSevice#addServerHost(com.bplow
	 * .netconn.aom.dao.entity.TbServerHost)
	 */
	@Override
	public void addServerHost(TbServerHost serverHost) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bplow.netconn.aom.service.ServerHostSevice#updateServerHost(com.bplow
	 * .netconn.aom.dao.entity.TbServerHost)
	 */
	@Override
	public void updateServerHost(TbServerHost serverHost) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bplow.netconn.aom.service.ServerHostSevice#deleteServerHost(com.bplow
	 * .netconn.aom.dao.entity.TbServerHost)
	 */
	@Override
	public void deleteServerHost(TbServerHost serverHost) {
		// TODO Auto-generated method stub

	}

}
