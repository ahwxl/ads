/**
 * www.bplow.com
 */
package com.bplow.netconn.aom.dao;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bplow.netconn.aom.dao.entity.TbWebServer;
import com.bplow.netconn.base.dao.pagination.IPagination;

/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年6月24日 下午10:02:19
 */
@ActiveProfiles("development")
@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)   
@Transactional
public class TbWebServerDaoTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TbWebServerDAO tbWebServerDao;
	
	@Test
	public void selectTest() throws SQLException{
		TbWebServer record = new TbWebServer();
		record.setMaxRowNums(10);
		record.setPageNum(0);
		record.setServerName("tes");
		record.setWebPath("1111");
		IPagination page = tbWebServerDao.queryWebServerForpage(record);
		
		logger.info("{}",page);
		
	}

}
