/**
 * 
 */
package com.bplow.netconn.aom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bplow.netconn.aom.service.impl.ExecuteShellCommand;

/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年4月17日 下午2:13:56
 */
@Profile(value="development")
@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ExecuteShellCommandTest {
	
	@Autowired
	ExecuteShellCommand executeCommand;
	
	@Test
	public void executeTest() throws Exception{
		executeCommand.setHost("115.28.182.11");
		executeCommand.setPassword("355");
		executeCommand.setPort("22");
		executeCommand.setUsername("root");
		executeCommand.setCommand("sh /home/wxl/ccbin/restart.sh");
		
		executeCommand.execute();
		
	}

}
