package com.bplow.netconn.aom.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bplow.netconn.aom.dao.TbWebServerDAO;
import com.bplow.netconn.aom.dao.entity.TbWebServer;
import com.bplow.netconn.aom.dao.entity.TbWebServerExample;
import com.bplow.netconn.base.dao.BaseIbatisDaoSupport;
import com.bplow.netconn.base.dao.pagination.IPagination;
import com.ibatis.sqlmap.client.SqlMapClient;

@Service("tbWebServerDAOImpl")
public class TbWebServerDAOImpl extends BaseIbatisDaoSupport implements
		TbWebServerDAO {

	public TbWebServerDAOImpl() {
		super();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_web_server
	 *
	 * @mbggenerated Sat Apr 23 12:36:53 CST 2016
	 */
	public TbWebServerDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_web_server
	 *
	 * @mbggenerated Sat Apr 23 12:36:53 CST 2016
	 */
	public int countByExample(TbWebServerExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject(
				"tb_web_server.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_web_server
	 *
	 * @mbggenerated Sat Apr 23 12:36:53 CST 2016
	 */
	public int deleteByExample(TbWebServerExample example) throws SQLException {
		int rows = sqlMapClient
				.delete("tb_web_server.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_web_server
	 *
	 * @mbggenerated Sat Apr 23 12:36:53 CST 2016
	 */
	public int deleteByPrimaryKey(String id) throws SQLException {
		TbWebServer _key = new TbWebServer();
		_key.setId(id);
		int rows = sqlMapClient
				.delete("tb_web_server.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_web_server
	 *
	 * @mbggenerated Sat Apr 23 12:36:53 CST 2016
	 */
	public void insert(TbWebServer record) throws SQLException {
		sqlMapClient.insert("tb_web_server.insert", record);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_web_server
	 *
	 * @mbggenerated Sat Apr 23 12:36:53 CST 2016
	 */
	public void insertSelective(TbWebServer record) throws SQLException {
		sqlMapClient.insert("tb_web_server.insertSelective", record);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_web_server
	 *
	 * @mbggenerated Sat Apr 23 12:36:53 CST 2016
	 */
	@SuppressWarnings("unchecked")
	public List<TbWebServer> selectByExample(TbWebServerExample example)
			throws SQLException {
		List<TbWebServer> list = sqlMapClient.queryForList(
				"tb_web_server.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_web_server
	 *
	 * @mbggenerated Sat Apr 23 12:36:53 CST 2016
	 */
	public TbWebServer selectByPrimaryKey(String id) throws SQLException {
		TbWebServer _key = new TbWebServer();
		_key.setId(id);
		TbWebServer record = (TbWebServer) sqlMapClient.queryForObject(
				"tb_web_server.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_web_server
	 *
	 * @mbggenerated Sat Apr 23 12:36:53 CST 2016
	 */
	public int updateByExampleSelective(TbWebServer record,
			TbWebServerExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update(
				"tb_web_server.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_web_server
	 *
	 * @mbggenerated Sat Apr 23 12:36:53 CST 2016
	 */
	public int updateByExample(TbWebServer record, TbWebServerExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("tb_web_server.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_web_server
	 *
	 * @mbggenerated Sat Apr 23 12:36:53 CST 2016
	 */
	public int updateByPrimaryKeySelective(TbWebServer record)
			throws SQLException {
		int rows = sqlMapClient.update(
				"tb_web_server.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_web_server
	 *
	 * @mbggenerated Sat Apr 23 12:36:53 CST 2016
	 */
	public int updateByPrimaryKey(TbWebServer record) throws SQLException {
		int rows = sqlMapClient.update("tb_web_server.updateByPrimaryKey",
				record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to
	 * the database table tb_web_server
	 *
	 * @mbggenerated Sat Apr 23 12:36:53 CST 2016
	 */
	protected static class UpdateByExampleParms extends TbWebServerExample {
		private Object record;

		public UpdateByExampleParms(Object record, TbWebServerExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bplow.netconn.aom.dao.TbWebServerDAO#queryWebServerForpage(com.bplow
	 * .netconn.aom.dao.entity.TbWebServer)
	 */
	@Override
	public IPagination queryWebServerForpage(TbWebServer record)
			throws SQLException {
		IPagination page = this.queryForPagenation(
				"tb_web_server.countForPage", "tb_web_server.selectForPage",
				record,record.getPageNum(),record.getMaxRowNums());
		return page;
	}
}