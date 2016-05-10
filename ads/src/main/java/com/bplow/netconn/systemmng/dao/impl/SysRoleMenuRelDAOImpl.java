package com.bplow.netconn.systemmng.dao.impl;

import com.bplow.netconn.systemmng.dao.SysRoleMenuRelDAO;
import com.bplow.netconn.systemmng.dao.entity.SysRoleMenuRel;
import com.bplow.netconn.systemmng.dao.entity.SysRoleMenuRelExample;
import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sysRoleMenuRelDAOImpl")
public class SysRoleMenuRelDAOImpl implements SysRoleMenuRelDAO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Sun May 08 15:13:45 CST 2016
     */
	@Autowired
    private SqlMapClient sqlMapClient;

    public SysRoleMenuRelDAOImpl() {
		super();
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Sun May 08 15:13:45 CST 2016
     */
    public SysRoleMenuRelDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Sun May 08 15:13:45 CST 2016
     */
    public int countByExample(SysRoleMenuRelExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("sys_role_menu_rel.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Sun May 08 15:13:45 CST 2016
     */
    public int deleteByExample(SysRoleMenuRelExample example) throws SQLException {
        int rows = sqlMapClient.delete("sys_role_menu_rel.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Sun May 08 15:13:45 CST 2016
     */
    public int deleteByPrimaryKey(Long id) throws SQLException {
        SysRoleMenuRel _key = new SysRoleMenuRel();
        _key.setId(id);
        int rows = sqlMapClient.delete("sys_role_menu_rel.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Sun May 08 15:13:45 CST 2016
     */
    public void insert(SysRoleMenuRel record) throws SQLException {
        sqlMapClient.insert("sys_role_menu_rel.insert", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Sun May 08 15:13:45 CST 2016
     */
    public void insertSelective(SysRoleMenuRel record) throws SQLException {
        sqlMapClient.insert("sys_role_menu_rel.insertSelective", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Sun May 08 15:13:45 CST 2016
     */
    @SuppressWarnings("unchecked")
    public List<SysRoleMenuRel> selectByExample(SysRoleMenuRelExample example) throws SQLException {
        List<SysRoleMenuRel> list = sqlMapClient.queryForList("sys_role_menu_rel.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Sun May 08 15:13:45 CST 2016
     */
    public SysRoleMenuRel selectByPrimaryKey(Long id) throws SQLException {
        SysRoleMenuRel _key = new SysRoleMenuRel();
        _key.setId(id);
        SysRoleMenuRel record = (SysRoleMenuRel) sqlMapClient.queryForObject("sys_role_menu_rel.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Sun May 08 15:13:45 CST 2016
     */
    public int updateByExampleSelective(SysRoleMenuRel record, SysRoleMenuRelExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("sys_role_menu_rel.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Sun May 08 15:13:45 CST 2016
     */
    public int updateByExample(SysRoleMenuRel record, SysRoleMenuRelExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("sys_role_menu_rel.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Sun May 08 15:13:45 CST 2016
     */
    public int updateByPrimaryKeySelective(SysRoleMenuRel record) throws SQLException {
        int rows = sqlMapClient.update("sys_role_menu_rel.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Sun May 08 15:13:45 CST 2016
     */
    public int updateByPrimaryKey(SysRoleMenuRel record) throws SQLException {
        int rows = sqlMapClient.update("sys_role_menu_rel.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Sun May 08 15:13:45 CST 2016
     */
    protected static class UpdateByExampleParms extends SysRoleMenuRelExample {
        private Object record;

        public UpdateByExampleParms(Object record, SysRoleMenuRelExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}