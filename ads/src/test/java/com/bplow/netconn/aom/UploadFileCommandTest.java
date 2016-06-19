/**
 * 
 */
package com.bplow.netconn.aom;

import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bplow.netconn.aom.command.UploadFileCommand;

/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年4月17日 上午10:33:16
 */
@ActiveProfiles("development")
@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UploadFileCommandTest {
	
	private UploadFileCommand uploadFile;
	
	@Test
	public void uploadTest(){
		InputStream in = this.getClass().getResourceAsStream("/log4j.xml");
		uploadFile.setDesPath("/home/wxl/");
		uploadFile.setSrcFileInput(in);
		uploadFile.execute();
	}

}
