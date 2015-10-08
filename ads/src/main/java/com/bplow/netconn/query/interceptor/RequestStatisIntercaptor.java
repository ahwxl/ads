package com.bplow.netconn.query.interceptor;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.bplow.netconn.base.utils.TraceNoGenerater;
import com.bplow.netconn.query.service.LogConfigService;
import com.bplow.netconn.statistics.domain.CustomerReq;
import com.bplow.netconn.statistics.service.SendReqService;
import com.bplow.netconn.statistics.service.customerReqService;
import com.bplow.netconn.statistics.task.CustomerReqTask;

public class RequestStatisIntercaptor extends HandlerInterceptorAdapter{
	
	@Autowired
	TraceNoGenerater traceNoGenerater;
	
	Logger log = LoggerFactory.getLogger("system_request");
	
	@Autowired
	private LogConfigService logConfigService;
	@Autowired
	private customerReqService customerReqService;
	@Autowired
	private SendReqService sendReqService;

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
	        String qstr      = request.getQueryString();
	        String cnidx     = request.getParameter("cnidx");
	        /*Enumeration<String> referenum  = request.getHeaderNames();
	        while(referenum.hasMoreElements()){
	        	String tmp = referenum.nextElement();
	        	String headervalue = request.getHeader(tmp);
	        	System.out.println(tmp+"="+headervalue );
	        	
	        	log.info("header:{}={}",tmp,headervalue);
	        }*/
	        //AccessRequired annotation = method.getAnnotation(AccessRequired.class);
	        //long starttime = System.currentTimeMillis();
	        if(null != cnidx && logConfigService.canSendMsgLog()){
	        	haddleCallBack(request);
	        }
	        //log.info("执行haddleCallBack耗时:[{}]",System.currentTimeMillis()-starttime);
	        if(logConfigService.enableLog()){
	        	log.info("REQUEST:[{},{},{},{},{},{}]",method.getName(),remoteIp,remoteUrl,qstr,refer,traceNo);
	        }
		}
        // 没有注解通过拦截
        return true;
    }
	
	private void haddleCallBack(HttpServletRequest request){
		String cnidx  = request.getParameter("cnidx");
		String id     = request.getParameter("id");
		String ext    = request.getParameter("ext");
		String mediaId = null;//媒体id
		String adNo   = null; //广告id
		String sessionId = request.getSession().getId();
		String refUrl     = request.getHeader("Referer");
		String reqUrl    = request.getQueryString();
		
		String [] adArray = ext.split("\\|");
		String [] customerIdArray = id.split("_");
		
		if(StringUtils.isNotBlank(cnidx)){
			int cnidxnum = Integer.parseInt(cnidx);
			if(cnidxnum <= adArray.length){
				adNo = adArray[cnidxnum-1];
			}
			if(cnidxnum <= customerIdArray.length){
				mediaId = customerIdArray[cnidxnum-1];
			}
		}
		CustomerReq cusreq = new CustomerReq(mediaId,  adNo, reqUrl,
			 refUrl, cnidx,  sessionId);
		customerReqService.executeSendTask(new CustomerReqTask(cusreq,sendReqService));
		
	}
	
	
}
