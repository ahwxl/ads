package com.bplow.netconn.query.interceptor;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.bplow.netconn.base.utils.TraceNoGenerater;


public class RequestStatisIntercaptor extends HandlerInterceptorAdapter{
	
	@Autowired
	TraceNoGenerater traceNoGenerater;
	
	Logger log = LoggerFactory.getLogger("system_request");

	public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
		String traceNo = traceNoGenerater.setNo();
		if(handler instanceof ResourceHttpRequestHandler){
			
		}else if(handler instanceof HandlerMethod ){
			HandlerMethod handlerMethod = (HandlerMethod) handler;
	        Method method    = handlerMethod.getMethod();
	        String remoteIp  = request.getRemoteHost();
	        String remoteUrl = request.getRequestURI();
	        String refer     = request.getHeader("Referer");
	        /*Enumeration<String> referenum  = request.getHeaderNames();
	        while(referenum.hasMoreElements()){
	        	String tmp = referenum.nextElement();
	        	String headervalue = request.getHeader(tmp);
	        	System.out.println(tmp+"="+headervalue );
	        	
	        	log.info("header:{}={}",tmp,headervalue);
	        }*/
	        //AccessRequired annotation = method.getAnnotation(AccessRequired.class);
	        log.info("REQUEST:[{},{},{},{},{}]",method.getName(),remoteIp,remoteUrl,refer,traceNo);
		}
		
        // 没有注解通过拦截
        return true;
    }
	
	
}
