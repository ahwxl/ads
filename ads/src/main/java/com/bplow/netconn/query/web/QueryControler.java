package com.bplow.netconn.query.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.velocity.VelocityView;

import com.bplow.netconn.systemmng.dao.entity.SysUser;


@Controller
public class QueryControler {

	
	@RequestMapping(value="/home",method = RequestMethod.GET)
	public String doWelcome(Map<String, Object> model,HttpServletRequest request){
		
		SysUser loginUser = (SysUser)request.getSession().getAttribute("lgu");
		if(null != loginUser){
			request.setAttribute("userName", loginUser.getUserName());
			model.put("userName", loginUser.getUserName());
		}
		
		return "index";
	}
	
	@RequestMapping(value="/query",method = RequestMethod.GET)
	public String doQuery(Map<String, Object> model){
		
		
		
		return "query";
	}
	
	@RequestMapping(value="/config",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
	public String doConfig(Map<String, Object> model){
		
		
		
		return "config";
	}
	
	@RequestMapping(value = "/SC/start", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public void obtainScript(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse respose) throws IOException {
		
		respose.setContentType("text/javascript");
		respose.setCharacterEncoding("UTF-8");
		
		OutputStream os =respose.getOutputStream();
		InputStream im = IOUtils.toInputStream("(function(a){alert(a)})('你好a')","UTF-8");
		
		IOUtils.copy(im, os);
		
		os.flush();
		os.close();

	}
	
	
}
