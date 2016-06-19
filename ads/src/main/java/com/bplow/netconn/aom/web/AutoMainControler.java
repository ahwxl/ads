/**
 * 
 */
package com.bplow.netconn.aom.web;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bplow.netconn.aom.dao.entity.TbServerHost;
import com.bplow.netconn.aom.dao.entity.TbWebServer;
import com.bplow.netconn.aom.service.ServerHostSevice;
import com.bplow.netconn.aom.service.WebServerService;

/**
 * @desc
 * @author wangxiaolei
 * @date 2016年4月17日 下午10:18:11
 */

@Controller
public class AutoMainControler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private WebServerService webSeverService;
	
	@Autowired
	private ServerHostSevice serverHostSevice;

	@RequestMapping(value = "/aom/main", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String aomMain(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("");
		

		return "aom/main";
	}

	/**********************主机管理**************************/
	
	/* 主机列表页面 */
	@RequestMapping(value = "/aom/serverHost", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String serverHostListPage(TbServerHost serverHost,HttpServletRequest request,
			HttpServletResponse response) {
		

		return "aom/serverHost";
	}
	
	/* 主机列表页面 */
	@RequestMapping(value = "/aom/serverHostList", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryServerHostList(TbServerHost serverHost,HttpServletRequest request,
			HttpServletResponse response) {

		String str = null;
		try {
		    str = serverHostSevice.queryServerHostList(serverHost);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return str;
	}

	/* 添加主机 */
	@RequestMapping(value = "/aom/addserverHost", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addServerHost(HttpServletRequest request,
			HttpServletResponse response) {

		return "aom/serverHost";
	}

	/* 查询主机 */
	@RequestMapping(value = "/aom/queryServerHost", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryServerHost(HttpServletRequest request,
			HttpServletResponse response) {

		return "aom/serverHost";
	}

	/* 修改主机 */
	@RequestMapping(value = "/aom/modifyServerHost", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String modifyServerHost(HttpServletRequest request,
			HttpServletResponse response) {

		return "aom/serverHost";
	}

	/* 删除主机 */
	@RequestMapping(value = "/aom/delServerHost", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deleteServerHost(HttpServletRequest request,
			HttpServletResponse response) {

		return "aom/serverHost";
	}
	
	/**********************应用服务器管理**************************/

	/* 主页面 */
	@RequestMapping(value = "/aom/webServer", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String webServerListPage(HttpServletRequest request,
			HttpServletResponse response) {

		return "aom/webServer";
	}
	
	/* 主机列表页面 */
	@RequestMapping(value = "/aom/webServerList", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String querywebServerList(HttpServletRequest request,
			HttpServletResponse response,TbWebServer tbWebServer) throws Exception {
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		tbWebServer.setPageNum(Integer.parseInt(start));
		tbWebServer.setMaxRowNums(Integer.parseInt(limit));
		
		String str = webSeverService.queryWebServerList(tbWebServer);

		return str;
	}

	/* 添加主机 */
	@RequestMapping(value = "/aom/addwebServer", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addwebServer(HttpServletRequest request,
			HttpServletResponse response,TbWebServer tbWebServer) throws Exception  {
		
		webSeverService.addWebServer(tbWebServer);

		return "{success:true,info:'ok'}";
	}

	/* 查询主机 */
	@RequestMapping(value = "/aom/querywebServer", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public TbWebServer querywebServer(HttpServletRequest request,
			HttpServletResponse response,TbWebServer tbWebServer) throws SQLException {

		TbWebServer webServer = webSeverService.queryWebServerById(tbWebServer.getId());
		
		return webServer;
	}

	/* 修改主机 */
	@RequestMapping(value = "/aom/modifywebServer", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String modifywebServer(HttpServletRequest request,
			HttpServletResponse response,TbWebServer tbWebServer) throws SQLException {

		webSeverService.updateWebServer(tbWebServer);
		
		return "aom/webServer";
	}

	/* 删除主机 */
	@RequestMapping(value = "/aom/delwebServer", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deletewebServer(HttpServletRequest request,
			HttpServletResponse response,TbWebServer tbWebServer) throws SQLException {

		webSeverService.deleteWebServer(tbWebServer);
		
		return "aom/webServer";
	}
	
	
	
	
}
