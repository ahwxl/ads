package com.bplow.netconn.query.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bplow.netconn.base.tmpl.VelocityHelper;
import com.bplow.netconn.query.dao.entity.Table;
import com.bplow.netconn.query.dao.entity.User;

@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class UserServiceTest {
	
	@Autowired
	public UserService userService;
	@Autowired
	VelocityHelper vh ;
	
	//@Test
	public void getUserListTest() throws SQLException, UnsupportedEncodingException{
		String mytext = java.net.URLEncoder.encode(":");
		User user = new User();
		//user.setId("测试2");
		user.setUserId("ibatis");
		userService.insertUser(user);
	}
	
	//@Test
	public void queryColumns(){
		String tablename = "user";
		Table table =new Table();
		List list = userService.queryColumns(tablename);
		table.setColumns(list);
		
		
	}
	
	@Test
	public void kickTest(){
		String id = "1000";
		//userService.putAd(id);
		for(int i =0 ;i < 100;i++){
			userService.kickIn(id);
		}
		
		
		userService.queryLiveAd(id);
		for(int i =0 ;i < 100;i++){
			userService.kickIn(id);
		}
		userService.queryLiveAd(id);
	}

}
