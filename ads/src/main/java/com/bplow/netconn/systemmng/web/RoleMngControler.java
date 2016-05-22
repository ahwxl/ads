/**
 * www.bplow.com
 */
package com.bplow.netconn.systemmng.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bplow.netconn.systemmng.domain.RoleDomain;

/**
 * @desc 角色管理
 * @author wangxiaolei
 * @date 2016年5月10日 下午10:01:02
 */
@Controller
public class RoleMngControler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/sys/roleMng")
	public String mainPage(Map<String, Object> model, RoleDomain role,
			HttpServletRequest request){
		logger.info("展示用户角色页面,{}",role);
		
		return "";
	}
	
	@RequestMapping(value = "/sys/roleList")
	@ResponseBody
	public String roleList(Map<String, Object> model, RoleDomain role,
			HttpServletRequest request){
		
		
		return "";
	}
	
	@RequestMapping(value = "/sys/addRole")
	@ResponseBody
	public String addRole(Map<String, Object> model, RoleDomain role,
			HttpServletRequest request){
		
		return "";
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
			HttpServletRequest request){
		
		return "";
	}
	
	@RequestMapping(value = "/sys/queryRole")
	@ResponseBody
	public String queryRoleById(Map<String, Object> model, RoleDomain role,
			HttpServletRequest request){
		
		return "";
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
