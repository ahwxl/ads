package com.bplow.netconn.io;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(locations = { "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class ProcessXmlTest {
	
	@Autowired
	private ProcessXmlService processXml ;
	
	@BeforeClass
	public static void setUp() throws Exception {
		//processXml = new ProcessXmlService();

	}
	
	@Test
	public void readXmlTest(){
		try {
			processXml.readXml();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
