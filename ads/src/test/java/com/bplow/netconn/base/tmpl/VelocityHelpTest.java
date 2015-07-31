package com.bplow.netconn.base.tmpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;


@ContextConfiguration(locations = { "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class VelocityHelpTest {
	
	@Autowired
	VelocityHelper vh ;
	
	//@Test
	public void rendTmplTest() throws Exception{
		vh.init();
		Map map = new HashMap();
		map.put("mymessage", "test test tes汪汪汪");
		InputStream in = this.getClass().getResourceAsStream("/table/vm/welcome.vm");
		vh.renderTemplate(map, in);
		
	}
	
	@Test
	public void rendTmpl2Test() throws Exception {
		vh.init();
		Map map = new HashMap();
		map.put("mymessage", "test test tes汪汪汪");
		File tempFile = File.createTempFile("middlegen", "tmp");
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		// InputStream in =
		// this.getClass().getResourceAsStream("/table/vm/welcome.vm");
		vh.renderTemplate(map, "/table/vm/entity.vm", writer);
		
		writer.flush();
        writer.close();
		
		File file = new File("D:/ddd.txt");
		file.createNewFile();
		FileUtils.copyFile(tempFile, file);
		
		
        
        

	}
	
	

}
