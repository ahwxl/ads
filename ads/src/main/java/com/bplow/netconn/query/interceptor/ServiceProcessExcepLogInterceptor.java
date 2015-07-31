package com.bplow.netconn.query.interceptor;

import java.io.Serializable;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceProcessExcepLogInterceptor implements MethodInterceptor, Serializable{

	Logger log = LoggerFactory.getLogger("system_error");
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		//log.info("进入服务区：");
		String className = invocation.getClass().getClass().getName();
		String methodName = invocation.getMethod().getName();
		Object obj = null;
		try {
			obj = invocation.proceed();
		} catch (Exception e) {
			log.error("服务执行异常", e);
		}
		//log.info("执行类{}，方法名{} end",className,methodName);
		return obj;
	}

}
