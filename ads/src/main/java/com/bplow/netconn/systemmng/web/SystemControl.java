package com.bplow.netconn.systemmng.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bplow.netconn.systemmng.dao.entity.SysUser;


@Controller
public class SystemControl {
	
	
	@RequestMapping(value="/obtainMenu")
	@ResponseBody
	public String obtainMenu(Map<String, Object> model,SysUser user,HttpServletRequest request){
		SysUser loginUser = (SysUser)request.getSession().getAttribute("lgu");
		String menujson = null;
		if(null != loginUser && "admin".equals(loginUser.getUserName())){
		    menujson ="[{'id':'apidocs','iconCls':'icon-docs','text':'功能菜单','singleClickExpand':true,'leaf':false,'children':["+
                    " {'href':'ad/customer','text':'客户管理','id':'客户管理','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true}," +
                    " {'href':'ad/addAdPage','text':'添加客户','id':'添加客户','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true},"+
                    "{'href':'ad/queryCustemDataPage','text':'媒体数据','id':'媒体数据','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true},"+
                    "{'href':'ad/mediaDataMng','text':'媒体数据管理','id':'媒体数据管理','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true},"+
                    "{'href':'ad/uploadCustemDataPage','text':'上传客户数据','id':'上传客户数据','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true}"+
     				"]}"+
                    ",{'id':'atuomanage','iconCls':'icon-docs','text':'运维管理','singleClickExpand':true,'leaf':false,'children':["
                    + "{'href':'aom/serverHost','text':'主机管理','id':'主机管理','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true},"
                    + "{'href':'aom/webServer','text':'web服务器','id':'web服务器','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true}"
                    + ""
                    + "]}"+
                    ",{'id':'systemmanage','iconCls':'icon-docs','text':'系统管理','singleClickExpand':true,'leaf':false,'children':[" + 
                    "{'href':'sys/userList','text':'系统用户','id':'系统用户','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true}," + 
                    "{'href':'sys/menuList','text':'菜单','id':'菜单','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true}," + 
                    "{'href':'sys/modulList','text':'模块','id':'模块','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true}," + 
                    "{'href':'sys/roleList','text':'角色','id':'角色','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true}," +
                    "{'href':'sys/orgMng','text':'机构','id':'机构','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true}" +
                    "]}"+
     		"]";
		}else if(null == loginUser){
			menujson ="[{'id':'apidocs','iconCls':'icon-docs','text':'功能菜单','singleClickExpand':true,'leaf':false,'children':["+
					"{'href':'ad/queryCustemDataPage','text':'媒体数据','id':'媒体数据','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true}"+
     				"]}"+
     		"]";
			
		}
		else{
			 menujson ="[{'id':'apidocs','iconCls':'icon-docs','text':'功能菜单','singleClickExpand':true,'leaf':false,'children':["
			 		+ "{'href':'ad/queryCustemDataPage','text':'媒体数据','id':'媒体数据','isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true}] }]";
		}
		return menujson;
	}

}
