package com.bplow.netconn.systemmng.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bplow.netconn.systemmng.dao.entity.User;
import com.bplow.netconn.systemmng.service.UserService;


@Controller
public class UserLoginAction {
	
	@Autowired
	@Qualifier("sysUserServiceImpl")
	private UserService userService;
	
	@RequestMapping(value="/loginAction",method = RequestMethod.POST)
	@ResponseBody
	public String doWelcome(Map<String, Object> model,User user,HttpServletRequest request){
		String returnjson = "{success:true,info:'密码错误'}";
		if(userService.loginAction(user, request)){
			returnjson = "{success:true,info:'ok'}";
		}
		
		return returnjson;
	}

}
