/**
 * www.bplow.com
 */
package com.bplow.netconn.aom.service;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bplow.netconn.aom.dao.entity.TbWebServer;


/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年6月19日 下午4:04:31
 */
@ActiveProfiles("development")
@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)   
@Transactional
public class WebSeverServiceTest {
	
	@Autowired
	private WebServerService webServerService;
	
	@Test
	public void addTest() throws SQLException{
		
		TbWebServer tbWebServer = new TbWebServer();
		tbWebServer.setHostId("1");
		tbWebServer.setServerName("test");
		tbWebServer.setServerPort("8080");
		tbWebServer.setWarName("acb");
		tbWebServer.setServerStatus("0");
		tbWebServer.setWebPath("/home/wxl/tomcat81");
		webServerService.addWebServer(tbWebServer);
		
	}
	

}
