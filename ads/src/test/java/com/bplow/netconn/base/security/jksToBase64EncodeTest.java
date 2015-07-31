package com.bplow.netconn.base.security;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(locations = { "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class jksToBase64EncodeTest {
	
	@Autowired
	jksToBase64Encode jksToBase64Encode;
	
	
	@Test
	public void todo() throws IOException{
		jksToBase64Encode.jksToBase64();
		
		
	}
	

}
