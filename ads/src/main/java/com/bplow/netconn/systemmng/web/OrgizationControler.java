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

import com.bplow.netconn.systemmng.domain.OrganizetionDomain;
import com.bplow.netconn.systemmng.service.OrganizationService;

/**
 * @desc 机构管理
 * @author wangxiaolei
 * @date 2016年5月10日 下午9:58:30
 */
@Controller
public class OrgizationControler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrganizationService organizationService;

	@RequestMapping(value = "/sys/orgMng")
	public String orgList(Map<String, Object> model, OrganizetionDomain org,
			HttpServletRequest request) {
	    
	    request.getSession().setAttribute("zhangsan", "展示");

		return "sys/org";
	}

	@RequestMapping(value = "/sys/orgList")
	@ResponseBody
	public String queryOrgList(Map<String, Object> model,
			OrganizetionDomain org, HttpServletRequest request) {
		logger.info("查询机构列表：{}", org);
		
		String obj = (String)request.getSession().getAttribute("zhangsan");
		logger.info("{}",obj);

		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		org.setPageNum(Integer.parseInt(start));
		org.setMaxRowNums(Integer.parseInt(limit));
		String str = organizationService.queryOrganizetionListForExtGrid(org);

		return str;
	}

	@RequestMapping(value = "/sys/addOrg")
	@ResponseBody
	public String addOrgAction(Map<String, Object> model,
			OrganizetionDomain org, HttpServletRequest request) {
		logger.info("添加机构：{}", org);
		organizationService.addOrganizetion(org);

		return "{success:true,info:'ok'}";
	}

	@RequestMapping(value = "/sys/delOrg")
	@ResponseBody
	public String delOrgAction(Map<String, Object> model,
			OrganizetionDomain org, HttpServletRequest request)
			throws Exception {
		logger.info("删除机构：{}", org);

		organizationService.deleteOrganizetion(org);

		return "{success:true,info:'操作成功'}";
	}

	@RequestMapping(value = "/sys/authoriseOrgUser")
	@ResponseBody
	public String authorizationOrgUserAction(Map<String, Object> model,
			OrganizetionDomain org, HttpServletRequest request)
			throws Exception {
		logger.info("删除机构：{}", org);

		organizationService.authorizeOrgUser(org);

		return "{success:true,info:'操作成功'}";
	}

}
