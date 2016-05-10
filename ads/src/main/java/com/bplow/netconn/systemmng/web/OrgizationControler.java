/**
 * www.bplow.com
 */
package com.bplow.netconn.systemmng.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bplow.netconn.systemmng.domain.OrganizetionDomain;
import com.bplow.netconn.systemmng.service.OrganizationService;

/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年5月10日 下午9:58:30
 */
@Controller
public class OrgizationControler {
	
	@Autowired
	private OrganizationService organizationService;
	
	@RequestMapping(value="/sys/orgMng")
	public String orgList(Map<String, Object> model,OrganizetionDomain org,HttpServletRequest request){
		
		
		return "sys/org";
	}
	
	@RequestMapping(value="/sys/orgList")
	@ResponseBody
	public String queryOrgList(Map<String, Object> model,OrganizetionDomain org,HttpServletRequest request){
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		org.setPageNum(Integer.parseInt(start));
		org.setMaxRowNums(Integer.parseInt(limit));
		String str = organizationService.queryOrganizetionListForExtGrid(org);
		
		return str;
	}
	
	@RequestMapping(value="/sys/addOrg")
	@ResponseBody
	public String addOrgAction(Map<String, Object> model,OrganizetionDomain org,HttpServletRequest request){
		
		organizationService.addOrganizetion(org);
		
		return "{success:true,info:'ok'}";
	}
	
	@RequestMapping(value="/sys/delOrg")
	@ResponseBody
	public String delOrgAction(Map<String, Object> model,OrganizetionDomain org,HttpServletRequest request){
		
		organizationService.addOrganizetion(org);
		
		return "{success:true,info:'ok'}";
	}

}
