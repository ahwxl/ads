package com.bplow.netconn.base.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;


@ContextConfiguration(locations = { "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class RSA_EncryptTest {
	
	@Autowired
	RSA_Encrypt rsa_Encrypt;
	
	@Test
	public void geraterKeyPair() throws Exception{
		
		rsa_Encrypt.generateKeyPair();
	}

}
