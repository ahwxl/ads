package com.bplow.netconn.aom.dao;


import java.sql.SQLException;
import java.util.List;

import com.bplow.netconn.aom.dao.entity.TbWebServer;
import com.bplow.netconn.aom.dao.entity.TbWebServerExample;
import com.bplow.netconn.base.dao.pagination.IPagination;

public interface TbWebServerDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    int countByExample(TbWebServerExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    int deleteByExample(TbWebServerExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    int deleteByPrimaryKey(String id) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    void insert(TbWebServer record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    void insertSelective(TbWebServer record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    List<TbWebServer> selectByExample(TbWebServerExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    TbWebServer selectByPrimaryKey(String id) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    int updateByExampleSelective(TbWebServer record, TbWebServerExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    int updateByExample(TbWebServer record, TbWebServerExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    int updateByPrimaryKeySelective(TbWebServer record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    int updateByPrimaryKey(TbWebServer record) throws SQLException;
    
    IPagination queryWebServerForpage(TbWebServer record) throws SQLException;
}