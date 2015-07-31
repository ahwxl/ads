package com.bplow.netconn.newcms.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class NewCmsControler {
	
	Logger log = LoggerFactory.getLogger(NewCmsControler.class);
	
	@RequestMapping(value = "/mainScreen",  produces = "text/html;charset=UTF-8")
	public String mainScreen(){
		
		
		return "cms/mainScreen";
	}
	
	
	@RequestMapping(value = "/prodlist/{catalogId}.htm", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String productList(@PathVariable("catalogId") String catalogId,Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse respose){
		log.info("请求参数是：{}",catalogId); 
		
		return "cms/productlist";
	}
	
	@RequestMapping(value = "/productdetail/{productId}_{pageId}.htm", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String productDetail(@PathVariable("productId") String productId,@PathVariable("pageId") String pageId,Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse respose){
		log.info("请求参数是：{},{}",productId,pageId);
		
		return "cms/productdetail";
	}

}
