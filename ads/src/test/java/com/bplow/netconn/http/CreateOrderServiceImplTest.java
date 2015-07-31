package com.bplow.netconn.http;


import org.junit.BeforeClass;
import org.junit.Test;

import com.bplow.netconn.io.ProcessIoService;



public class CreateOrderServiceImplTest{
	
	private static CreateOrderServiceImpl createOrder;
	private static String loginUrl ="http://api.test.alipay.net/home/auto_login_by_guest.htm?service=http%3A%2F%2Fapi.test.alipay.net%2Fhome%2Fcas_security_check.htm";
	private static String createUrlStable = "http://api.test.alipay.net/atinterface/execute_api.htm";
	
	@BeforeClass
	public static void setUp() throws Exception {
		createOrder = new CreateOrderServiceImpl();
		createOrder.setLoginUrl(loginUrl);
		createOrder.setCreateUrlStable(createUrlStable);
		createOrder.setProcessIo(new ProcessIoService());
	}
	
	@Test
	public void createOrderTest(){
		createOrder.createOrder();
	}

}
