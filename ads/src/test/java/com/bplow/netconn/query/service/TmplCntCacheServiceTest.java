package com.bplow.netconn.query.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class TmplCntCacheServiceTest {
	
	@Autowired
	TmplCntCacheService tmplCntCacheService;
	
	private final String [] tmplarray = new String[]{"/js/base.min.js",
			"/js/adcommp.min.js","1","2","3","4","4","4","4","/js/MZADX.js","4","4","/js/YOUDAO.js","/js/ALLYES.js"
	};
	
	@Test
	public void test(){
		for(String str : tmplarray){
		   Object obj = tmplCntCacheService.getTmplByKey(str);
		   if(null != obj)
		   System.out.println(obj.toString());
		}
		
		
	}

}
