package com.bplow.netconn.query.dao.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.bplow.netconn.base.dao.HQLEntity;
import com.bplow.netconn.query.dao.AdDao;
import com.bplow.netconn.query.dao.entity.Ad;
import com.bplow.netconn.query.dao.entity.Columns;
import com.bplow.netconn.query.dao.entity.CustomerData;
import com.ibatis.sqlmap.client.SqlMapClient;

@Component
@Transactional
public class AdDaoImpl implements AdDao{

	private static Logger logger = LoggerFactory.getLogger(AdDaoImpl.class);
	
	private String queryCustomerData ="select id,customer_id,customer_name,ad_addr,show_num,click_num,income,upload_data,gmt_create,gmt_modify  from tb_customer_data a  ";
	
	private String insertCustomerData = "insert into tb_customer_data(	customer_id ,	customer_name ,	ad_addr ,	show_num ,	click_num ,	income ,	upload_data ,	gmt_create ,	gmt_modify ) values"
			+ "(?,?,?,?,?,?,?,now(),now())";
	
	@Autowired
	private SqlMapClient sqlMapClient;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void insertAd(Ad ad) throws SQLException {
		sqlMapClient.insert("insertAd",ad);
	}

	@Override
	public Ad queryAd(int id) throws SQLException {
		logger.info("查询客户by id");
		Map para = new HashMap();
		para.put("id", id);
		Ad ad = (Ad)sqlMapClient.queryForObject("queryAdById", id);
		return ad;
	}
	
	public int delAd(int id) throws SQLException {
		Map para = new HashMap();
		para.put("id", id);
		int ad = sqlMapClient.delete("delAd", id);
		return ad;
	}
	
	public int upateAd(int id) throws SQLException {
		Map para = new HashMap();
		para.put("id", id);
		int tmpid = sqlMapClient.update("queryAdById", id);
		return tmpid;
	}

	@Override
	public List queryAdList(Ad ad) throws SQLException {
		logger.info("查询所有客户。");
		//Assert.isNull(ad, "参数不合法");
		List list = sqlMapClient.queryForList("queryAdForList",ad);
		return list;
	}

	@Override
	public void batchInsertCustomerData(final List<CustomerData> data)
			throws SQLException {
		int[] updateCounts = jdbcTemplate.batchUpdate(insertCustomerData,
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setString(1, data.get(i).getCustomerId());
						ps.setString(2, data.get(i).getCustomerName());
						ps.setString(3, data.get(i).getAdAddr());
						ps.setDouble(4, data.get(i).getShowNum());
						ps.setDouble(5, data.get(i).getClickNum());
						ps.setDouble(6, data.get(i).getIncome());
						ps.setDate(7, new java.sql.Date(data.get(i).getUploadData().getTime()));
					}

					public int getBatchSize() {
						return data.size();
					}
				});

	}

	@Override
	public List queryCustomerData(CustomerData customer) throws SQLException {
		HQLEntity hqlEntity = new HQLEntity(queryCustomerData);
		hqlEntity.append(" where a.customer_id = ? ","1");
		//媒体名称
		if(StringUtils.isNotBlank(customer.getCustomerName())){
			hqlEntity.append(" and a.customer_name like ? ",customer.getCustomerName());
		}

		hqlEntity.append("limit 0,10");
		logger.info("查询媒体数据SQL[{}]",hqlEntity.gethqlSql());
		return jdbcTemplate.query(hqlEntity.gethqlSql(), new RowMapper<CustomerData>() {
            public CustomerData mapRow(ResultSet rs, int rowNum) throws SQLException {
            	CustomerData customer = new CustomerData();
            	customer.setCustomerId(rs.getString("customer_id"));
            	customer.setCustomerName(rs.getString("customer_name"));
            	customer.setAdAddr(rs.getString("ad_addr"));
            	customer.setClickNum(rs.getDouble("click_num"));
            	customer.setShowNum(rs.getDouble("show_num"));
            	customer.setIncome(rs.getDouble("income"));
            	customer.setUploadData(rs.getDate("upload_data"));
                return customer;
            }
        },hqlEntity.getParamObjArray());
	}

}
