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
public class Base64EncodeTest {
	
	@Autowired
	Base64Encode base64Encode;
	
	@Test
	public void encode() throws IOException{
		
		base64Encode.encodeString("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/I5wFr4pQLv9gogstsQ5TsZX/9rKvpSWTax1u6FTQdphQD+pvb76UvLhwYMGkOLZpLmIxnwdn3n4sbraPL5nuN7LR+LlhevfnwqQ12EIf7LNnbK7NqPHWbaojBzCpqBs6yMbYAUu/TFHHgkalia9To4sHObA4/XHrLGPb4MI0XwIDAQAB");
		
		base64Encode.encodeString("123456");
	}
	
	
	public void decode(){
		
		
	}

}
