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

import com.bplow.netconn.systemmng.domain.OrganizetionDomain;

/**
 * @desc 模块管理
 * @author wangxiaolei
 * @date 2016年5月10日 下午10:01:25
 */
@Controller
public class ModuleMngControler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/sys/moduleMng")
	public String orgList(Map<String, Object> model, OrganizetionDomain org,
			HttpServletRequest request) {
	    
	    request.getSession().setAttribute("zhangsan", "展示");

		return "sys/module";
	}

}
