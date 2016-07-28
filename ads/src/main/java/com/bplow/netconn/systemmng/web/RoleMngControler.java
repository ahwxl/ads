/**
 * www.bplow.com
 */
package com.bplow.netconn.systemmng.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bplow.netconn.base.dao.pagination.IPagination;
import com.bplow.netconn.systemmng.dao.entity.SysRole;
import com.bplow.netconn.systemmng.domain.RoleDomain;
import com.bplow.netconn.systemmng.service.RoleService;

/**
 * @desc 角色管理
 * @author wangxiaolei
 * @date 2016年5月10日 下午10:01:02
 */
@Controller
public class RoleMngControler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "/sys/roleMng")
	public String mainPage(Map<String, Object> model, RoleDomain role,
			HttpServletRequest request){
		logger.info("展示用户角色页面,{}",role);
		
		return "sys/role";
	}
	
	@RequestMapping(value = "/sys/roleList")
	@ResponseBody
	public String roleList(Map<String, Object> model, SysRole role,
			HttpServletRequest request) throws Exception{
		
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		role.setPageNum(Integer.parseInt(start));
		role.setMaxRowNums(Integer.parseInt(limit));
		
		IPagination page = roleService.queryRolePage(role);
		
		return page.getJsonByList();
	}
	
	@RequestMapping(value = "/sys/addRole")
	@ResponseBody
	public String addRole(Map<String, Object> model, RoleDomain role,
			HttpServletRequest request) throws Exception{
		
		roleService.addRole(role);
		
		return "{success:true,info:'ok'}";
	}
	
	@RequestMapping(value = "/sys/modifyRole")
	@ResponseBody
	public String modifyRole(Map<String, Object> model, RoleDomain role,
			HttpServletRequest request){
		
		return "";
	}
	
	@RequestMapping(value = "/sys/deleteRole")
	@ResponseBody
	public String deleletRole(Map<String, Object> model, RoleDomain role,
			HttpServletRequest request) throws Exception{
		
		roleService.deleteRole(role);
		
		return "{success:true,info:'ok'}";
	}
	
	@RequestMapping(value = "/sys/queryRole")
	@ResponseBody
	public String queryRoleById(Map<String, Object> model, SysRole role,
			HttpServletRequest request) throws Exception{
		
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		role.setPageNum(Integer.parseInt(start));
		role.setMaxRowNums(Integer.parseInt(limit));
		
		IPagination page = roleService.queryRolePage(role);
		
		return page.getJsonByList();
	}
	
	/**
	 * 授权用户角色
	 * @return
	 */
	@RequestMapping(value = "/sys/authorizeUserRole")
	@ResponseBody
	public String authorizeUserRoles(){
		
		return "";
	}
	
	/**
	 * 授权菜单角色
	 * @return
	 */
	@RequestMapping(value = "/sys/authorizeMenuRole")
	@ResponseBody
	public String authorizeMenuRoles(){
		
		
		return "";
	}

}
