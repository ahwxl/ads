package com.bplow.netconn.newcms.dao;

import com.bplow.netconn.newcms.domain.FmContent;

public interface ContentJdbcDao {
	
	/**
	 * 获取文章内容
	 * @param id
	 * @return
	 */
	public FmContent getContentById(String id);

}
