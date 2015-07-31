package com.bplow.netconn.newcms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.lob.LobHandler;

import com.bplow.netconn.newcms.dao.ContentJdbcDao;
import com.bplow.netconn.newcms.domain.FmContent;

public class ContentJdbcDaoImpl implements ContentJdbcDao{

	@Autowired
	private LobHandler lobHandler;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * 获取单条文章内容
	 */
	@Override
	public FmContent getContentById(String id) {
		
		return jdbcTemplate.queryForObject("select cnt_caption,content from fm_content"/*,new String[]{tablename}*/, new RowMapper<FmContent>() {
            public FmContent mapRow(ResultSet rs, int rowNum) throws SQLException {
            	FmContent cnt = new FmContent();
                cnt.setCntCaption(rs.getString("cnt_caption"));
                cnt.setContent(lobHandler.getBlobAsBytes(rs, rowNum));
                return cnt;
            }
        });
	}

}
