package com.bplow.netconn.query.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.bplow.netconn.query.dao.TableDao;
import com.bplow.netconn.query.dao.entity.Columns;

@Service
public class TableJdbcDaoImpl implements TableDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List queryTable(String tablename) {
		
		return jdbcTemplate.query("show columns from "+tablename/*,new String[]{tablename}*/, new RowMapper<Columns>() {
            public Columns mapRow(ResultSet rs, int rowNum) throws SQLException {
            	Columns actor = new Columns();
                actor.setName(rs.getString("field"));
                actor.setJtype(rs.getString("type"));
                return actor;
            }
        });
		
	}

}
