package com.bplow.netconn.systemmng.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bplow.netconn.systemmng.dao.entity.User;


@Controller
public class SystemControl {
	
	
	@RequestMapping(value="/obtainMenu")
	@ResponseBody
	public String obtainMenu(Map<String, Object> model,User user,HttpServletRequest request){
		User loginUser = (User)request.getSession().getAttribute("lgu");
		String menujson = null;
		if(null != loginUser && "admin".equals(loginUser.getUserName())){
		    menujson ="[{'id':'apidocs','iconCls':'icon-docs','text':'功能菜单','singleClickExpand':true,'leaf':false,'children':["+
                    " {'href':'ad/customer','text':'客户管理','id':'客户管理','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true}," +
                    " {'href':'ad/addAdPage','text':'添加客户','id':'添加客户','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true},"+
                    "{'href':'ad/queryCustemDataPage','text':'媒体数据','id':'媒体数据','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true},"+
                   "  {'href':'ad/uploadCustemDataPage','text':'上传客户数据','id':'上传客户数据','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true}"+
     				"]}"+
     		"]";
		}else{
			 menujson ="[{'id':'apidocs','iconCls':'icon-docs','text':'功能菜单','singleClickExpand':true,'leaf':false,'children':[{'href':'ad/queryCustemDataPage','text':'媒体数据','id':'媒体数据','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true}] }]";
		}
		return menujson;
	}

}
