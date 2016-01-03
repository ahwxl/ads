package com.bplow.netconn.systemmng.web;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bplow.netconn.systemmng.dao.entity.User;
import com.bplow.netconn.systemmng.service.UserService;


/**
 * 
 * @desc 用户信息添加、删除、修改、查询、密码修改、密码找回
 * @author wangxiaolei
 * @date 2016年1月3日 下午4:35:36
 */
@Controller
public class UserControler {
	
	@Autowired
	@Qualifier(value="sysUserServiceImpl")
	private UserService userService;
	
	/**
	 * 展示用户列表
	 * @throws SQLException 
	 */
	@RequestMapping(value="/sys/userList")
	public String queryUserList(Map<String, Object> model,User user,HttpServletRequest request) 
			throws SQLException{
		
		
		
		return "sys/user";
	}
	
	
	/**
	 * 展示用户列表
	 * @throws SQLException 
	 */
	@RequestMapping(value="/sys/queryUserList")
	@ResponseBody
	public String queryUserListAction(Map<String, Object> model,User user,HttpServletRequest request) 
			throws SQLException{
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		user.setPageNum(Integer.parseInt(start));
		user.setMaxRowNums(Integer.parseInt(limit));
		String resposeStr = userService.queryUserList(user);
		
		return resposeStr;
	}
	
	/**
	 * 用户添加
	 */
	
	/**
	 * 用户删除
	 */
	
	/**
	 * 展示单个用户信息
	 */
	
	/**
	 * 用户修改
	 */
	
	/**
	 * 密码找回
	 */
	
	
	/**
	 * 重置密码
	 */
	
	
	
	

}
