/**
 * 
 */
package com.bplow.netconn.aom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bplow.netconn.aom.command.ExecuteShellCommand;

/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年4月17日 下午2:13:56
 */
@ActiveProfiles("development")
@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ExecuteShellCommandTest {
	
	ExecuteShellCommand executeCommand = new ExecuteShellCommand();
	
	@Test
	public void executeTest() throws Exception{
		executeCommand.setHost("115.28.2.166");
		executeCommand.setPassword("355");
		executeCommand.setPort(22);
		executeCommand.setUsername("root");
		executeCommand.setCommand("/home/wxl/ccbin/restart.sh tomcat85");
		
		executeCommand.init();
		
		executeCommand.execute();
		
		
	}

}
