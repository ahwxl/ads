package com.bplow.netconn.statistics.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.bplow.netconn.statistics.dao.StatisDao;
import com.bplow.netconn.statistics.domain.CustomerReq;

@Component
public class StatisJdbcDaoImpl implements StatisDao{

	private String insertCustomerData = "insert into td_media_req(	mediaId ,	adNo ,	reqUrl ,	refUrl ,	extNo ,	sessionId ,	gmt_create ) values"
			+ "(?,?,?,?,?,?,now())";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void lazyInsertReqMsg(final List<CustomerReq> listData) {
		
		int[] updateCounts = jdbcTemplate.batchUpdate(insertCustomerData,
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setString(1, listData.get(i).getMediaId());
						ps.setString(2, listData.get(i).getAdNo());
						ps.setString(3, listData.get(i).getReqUrl());
						ps.setString(4, listData.get(i).getRefUrl());
						ps.setString(5, listData.get(i).getExtNo());
						ps.setString(6, listData.get(i).getSessionId());
					}

					public int getBatchSize() {
						return listData.size();
					}
				});

	}

}
