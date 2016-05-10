package com.bplow.netconn.systemmng.web;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bplow.netconn.systemmng.dao.entity.SysUser;
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
	public String queryUserList(Map<String, Object> model,SysUser user,HttpServletRequest request) 
			throws SQLException{
		
		
		
		return "sys/user";
	}
	
	
	/**
	 * 展示用户列表
	 * @throws SQLException 
	 */
	@RequestMapping(value="/sys/queryUserList")
	@ResponseBody
	public String queryUserListAction(Map<String, Object> model,SysUser user,HttpServletRequest request) 
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
	@RequestMapping(value="/sys/addUser")
	@ResponseBody
	public String addUserAction(Map<String, Object> model,SysUser user,HttpServletRequest request) 
			throws SQLException{

		userService.addUser(user);
		
		return "{success:true,info:'操作成功!'}";
	}
	
	/**
	 * 用户删除
	 */
	@RequestMapping(value="/sys/delUser")
	@ResponseBody
	public String delUserAction(Map<String, Object> model,SysUser user,HttpServletRequest request) 
			throws SQLException{

		userService.delUser(user);
		
		return "{success:true,info:'操作成功!'}";
	}
	
	/**
	 * 展示单个用户信息
	 */
	@RequestMapping(value="/sys/queryUserInfo")
	@ResponseBody
	public String queryUserInfo(Map<String, Object> model,SysUser user,HttpServletRequest request) 
			throws SQLException{
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		user.setPageNum(Integer.parseInt(start));
		user.setMaxRowNums(Integer.parseInt(limit));
		String resposeStr = userService.queryUserList(user);
		
		return resposeStr;
	}
	
	/**
	 * 用户修改
	 */
	@RequestMapping(value="/sys/updateUserInfo")
	@ResponseBody
	public String updateUserInfoAction(Map<String, Object> model,SysUser user,HttpServletRequest request) 
			throws SQLException{
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		user.setPageNum(Integer.parseInt(start));
		user.setMaxRowNums(Integer.parseInt(limit));
		String resposeStr = userService.queryUserList(user);
		
		return resposeStr;
	}
	
	/**
	 * 密码找回
	 */
	@RequestMapping(value="/sys/findBackUserAccount")
	@ResponseBody
	public String findBackUserAccount(Map<String, Object> model,SysUser user,HttpServletRequest request) 
			throws SQLException{
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		user.setPageNum(Integer.parseInt(start));
		user.setMaxRowNums(Integer.parseInt(limit));
		String resposeStr = userService.queryUserList(user);
		
		return resposeStr;
	}
	
	
	/**
	 * 重置密码
	 */
	@RequestMapping(value="/sys/updateUserPw")
	@ResponseBody
	public String updateUserPw(Map<String, Object> model,SysUser user,HttpServletRequest request) 
			throws SQLException{
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		user.setPageNum(Integer.parseInt(start));
		user.setMaxRowNums(Integer.parseInt(limit));
		String resposeStr = userService.queryUserList(user);
		
		return resposeStr;
	}
	
	
	

}
