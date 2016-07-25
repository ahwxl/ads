/**
 * www.bplow.com
 */
package com.bplow.netconn.systemmng.web;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bplow.netconn.base.dao.pagination.IPagination;
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
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/sys/menuMng")
	public String main(Map<String, Object> model, MenuDomain menu,
			HttpServletRequest request){
		logger.info("展示菜单列表页面,{}",menu);
		
		return "sys/menu";
	}
	
	@RequestMapping(value = "/sys/menuList")
	@ResponseBody
	public String menulist(Map<String, Object> model, MenuDomain menu,
			HttpServletRequest request) throws Exception{
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		menu.setPageNum(Integer.parseInt(start));
		menu.setMaxRowNums(Integer.parseInt(limit));
		
		IPagination pagination = menuService.queryMenuPage(menu);
		
		return pagination.getJsonByList();
	}
	
	@RequestMapping(value = "/sys/addMenu")
	@ResponseBody
	public String addMenu(Map<String, Object> model, MenuDomain menu,
			HttpServletRequest request) throws SQLException{
		
		menuService.addMenu(menu);
	
		return "{success:true,info:'ok'}";
	}
	
	@RequestMapping(value = "/sys/deleteMenu")
	@ResponseBody
	public String deleteMenu(Map<String, Object> model, MenuDomain menu,
			HttpServletRequest request) throws Exception{
		
		menuService.deleteMenu(menu);
		
		return "{success:true,info:'ok'}";
	}
	
	@RequestMapping(value = "/sys/modifyMenu")
	@ResponseBody
	public String modifyMenu(Map<String, Object> model, MenuDomain menu,
			HttpServletRequest request) throws Exception{
		
		menuService.updateMenu(menu);
		
		return "{success:true,info:'ok'}";
	}
	
	@RequestMapping(value = "/sys/queryMenu")
	@ResponseBody
	public String queryMenuById(Map<String, Object> model, MenuDomain menu,
			HttpServletRequest request) throws Exception{
		
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		menu.setPageNum(0);
		menu.setMaxRowNums(10);
		
		IPagination pagination = menuService.queryMenuPage(menu);
		
		return pagination.getJsonByList();
	}

}
