/**
 * www.bplow.com
 */
package com.bplow.deep.bpm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bplow.deep.bpm.service.BmpService;

/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年7月12日 下午9:34:43
 */
@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@ActiveProfiles("development")
public class BpmServiceTest {
	
	@Autowired
	private BmpService bmpService;
	
	@Test
	public void deployTest(){
		bmpService.deploy("12445");
	}

}
