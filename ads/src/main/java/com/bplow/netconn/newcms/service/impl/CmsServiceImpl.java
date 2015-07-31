package com.bplow.netconn.newcms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bplow.netconn.newcms.dao.CatalogDao;
import com.bplow.netconn.newcms.dao.ContentDao;
import com.bplow.netconn.newcms.dao.ContentJdbcDao;
import com.bplow.netconn.newcms.domain.FmCatalog;
import com.bplow.netconn.newcms.service.CmsService;

public class CmsServiceImpl implements CmsService{
	
	@Autowired
	CatalogDao catalogDao;
	@Autowired
	ContentDao contentDao;
	@Autowired
	ContentJdbcDao contentJdbcDao;
	
	public List getCatalogList(String pcatalogId){
		
		
		return null;
	}

	@Override
	public List queryAllCatalogByP(String catalogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContentById(String cntId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getContentListByCatalog(String catalogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FmCatalog getCatalogById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
