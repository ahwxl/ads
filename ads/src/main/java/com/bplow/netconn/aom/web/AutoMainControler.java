/**
 * 
 */
package com.bplow.netconn.aom.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年4月17日 下午10:18:11
 */

@Controller
public class AutoMainControler {
	
	
	@RequestMapping(value = "/aom/main", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String adMain(){
		
		
		return "aom/index";
	}

}
