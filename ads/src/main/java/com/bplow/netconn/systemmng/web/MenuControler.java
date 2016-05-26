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

import com.bplow.netconn.systemmng.domain.MenuDomain;
import com.bplow.netconn.systemmng.service.MenuService;

/**
 * @desc 菜单管理
 * @author wangxiaolei
 * @date 2016年5月22日 上午10:17:56
 */
@Controller
public class MenuControler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/sys/menuMng")
	public String main(Map<String, Object> model, MenuDomain menu,
			HttpServletRequest request){
		logger.info("展示菜单列表页面,{}",menu);
		
		return "";
	}
	
	@RequestMapping(value = "/sys/menuList")
	@ResponseBody
	public String menulist(Map<String, Object> model, MenuDomain menu,
			HttpServletRequest request){
		
		return "";
	}
	
	@RequestMapping(value = "/sys/addMenu")
	@ResponseBody
	public String addMenu(Map<String, Object> model, MenuDomain menu,
			HttpServletRequest request){
	
		return "";
	}
	
	@RequestMapping(value = "/sys/deleteMenu")
	@ResponseBody
	public String deleteMenu(Map<String, Object> model, MenuDomain menu,
			HttpServletRequest request){
		
		return "";
	}
	
	@RequestMapping(value = "/sys/modifyMenu")
	@ResponseBody
	public String modifyMenu(Map<String, Object> model, MenuDomain menu,
			HttpServletRequest request){
		
		return "";
	}
	
	@RequestMapping(value = "/sys/queryMenu")
	@ResponseBody
	public String queryMenuById(Map<String, Object> model, MenuDomain menu,
			HttpServletRequest request){
		
		return "";
	}

}
