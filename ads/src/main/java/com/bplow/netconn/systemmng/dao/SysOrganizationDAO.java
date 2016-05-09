package com.bplow.netconn.systemmng.dao;

import com.bplow.netconn.systemmng.dao.entity.SysOrganization;
import com.bplow.netconn.systemmng.dao.entity.SysOrganizationExample;
import java.sql.SQLException;
import java.util.List;

public interface SysOrganizationDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_organization
     *
     * @mbggenerated Sun May 08 15:12:46 CST 2016
     */
    int countByExample(SysOrganizationExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_organization
     *
     * @mbggenerated Sun May 08 15:12:46 CST 2016
     */
    int deleteByExample(SysOrganizationExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_organization
     *
     * @mbggenerated Sun May 08 15:12:46 CST 2016
     */
    int deleteByPrimaryKey(Integer id) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_organization
     *
     * @mbggenerated Sun May 08 15:12:46 CST 2016
     */
    void insert(SysOrganization record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_organization
     *
     * @mbggenerated Sun May 08 15:12:46 CST 2016
     */
    void insertSelective(SysOrganization record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_organization
     *
     * @mbggenerated Sun May 08 15:12:46 CST 2016
     */
    List<SysOrganization> selectByExample(SysOrganizationExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_organization
     *
     * @mbggenerated Sun May 08 15:12:46 CST 2016
     */
    SysOrganization selectByPrimaryKey(Integer id) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_organization
     *
     * @mbggenerated Sun May 08 15:12:46 CST 2016
     */
    int updateByExampleSelective(SysOrganization record, SysOrganizationExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_organization
     *
     * @mbggenerated Sun May 08 15:12:46 CST 2016
     */
    int updateByExample(SysOrganization record, SysOrganizationExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_organization
     *
     * @mbggenerated Sun May 08 15:12:46 CST 2016
     */
    int updateByPrimaryKeySelective(SysOrganization record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_organization
     *
     * @mbggenerated Sun May 08 15:12:46 CST 2016
     */
    int updateByPrimaryKey(SysOrganization record) throws SQLException;
}