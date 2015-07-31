package com.bplow.netconn.base.utils;

import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;

@Service
public class TraceNoGenerater {
	
	private final Lock queueLock = new ReentrantLock();
	
	ThreadLocal<String> traceNo = new ThreadLocal<String>();
	
	
	public String getNo(){
		queueLock.lock();
		String traceNoStr = traceNo.get();
		if(traceNoStr == null){
			traceNo.set(UUID.randomUUID().toString());
		}
		traceNoStr = traceNo.get();
		queueLock.unlock();
		return traceNoStr;
	}
	
	public String setNo(){
		String traceNoStr = UUID.randomUUID().toString();
		traceNo.set(traceNoStr);
		return traceNoStr;
	}

}
