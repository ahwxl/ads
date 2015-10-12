package com.bplow.netconn.query.web;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bplow.netconn.query.dao.entity.Ad;
import com.bplow.netconn.query.module.ReqForm;
import com.bplow.netconn.query.service.Adservice;


@Controller
public class JsControler {
	
	@Autowired
	private Adservice adService;
	
	@RequestMapping(value = "/SC/main", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String mainPage(){
		
		
		return "ad/main";
	}
	
	/**
	 * 获取基础 basejs
	 * 
	 * 
	 * @param model
	 * @param reqForm
	 * @param request
	 * @param respose
	 * @throws IOException
	 */
	@RequestMapping(value = "/SC/base", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String obtainScript(Map<String, Object> model,ReqForm reqForm,
			HttpServletRequest request, HttpServletResponse respose) throws IOException {
		respose.setContentType("text/javascript");
		respose.setCharacterEncoding("UTF-8");
		String queryStr = request.getQueryString();
		reqForm.setQueryStr(queryStr);
		String baseJsPath = "/js/base.min.js";
		//InputStream in = this.getClass().getResourceAsStream("");
		String str = adService.obtionBaseScript(reqForm, baseJsPath);
		//InputStream im = IOUtils.toInputStream("(function(a){alert(a)})('你好a')","UTF-8");
		/*OutputStream out = respose.getOutputStream();
		InputStream im = IOUtils.toInputStream(str);
		IOUtils.copy(im, out);
		out.flush();
		out.close();*/
		return str;

	}
	
	
	@RequestMapping(value = "/SC/over", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public void obtainScript2(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse respose) throws IOException {
		
		respose.setContentType("text/javascript");
		respose.setCharacterEncoding("UTF-8");
		
		OutputStream os =respose.getOutputStream();
		String tplpath = "/js/adcommp.min.js";
		String tplcnt = adService.requestCustomerJs(tplpath);		
		InputStream in = IOUtils.toInputStream(tplcnt);
		
		//InputStream im = IOUtils.toInputStream("(function(a){alert(a)})('你好a')","UTF-8");
		
		IOUtils.copy(in, os);
		
		os.flush();
		os.close();

	}
	/**
	 * 请求执行方法
	 * execute?id=cnd2_cnd3&c=Sellbuyads_1431227010526721&cnidx=1&ext=7577|1387
	 * 根据 cnidx 来推断目标加载的ad js
	 * 如果有回流  该方法会再被请求一次 cnidx 加1
	 * @param model
	 * @param request
	 * @param respose
	 * @throws IOException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/SC/execute", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public void obtainScript3(Map<String, Object> model,ReqForm reqForm,
			HttpServletRequest request, HttpServletResponse respose) throws IOException, SQLException {
		respose.setContentType("text/javascript");
		respose.setCharacterEncoding("UTF-8");
		String refer = request.getHeader("Referer");
		String sessionId = request.getSession().getId();
		String queryStr = request.getQueryString();
		reqForm.setQueryStr(queryStr);
		reqForm.setRefUrl(refer);
		reqForm.setSessionId(sessionId);
		OutputStream os =respose.getOutputStream();
		
		String str = adService.executeMethod(reqForm, os);
		if(null != str){
			InputStream in = IOUtils.toInputStream(str);
			IOUtils.copy(in, os);
		}
		os.flush();
		os.close();
	}
	/**
	 * 加载包装客户 js 
	 * 
	 * @param customerName
	 * @param model
	 * @param request
	 * @param respose
	 * @throws IOException
	 */
	@RequestMapping(value = "/SC/{customerName}/js", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public void obtainScript4(@PathVariable("customerName") String customerName,Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse respose) throws IOException {
		
		respose.setContentType("text/javascript");
		respose.setCharacterEncoding("UTF-8");
		String custName = customerName;
		OutputStream os =respose.getOutputStream();
		
		String tplpath = "/js/"+custName+".js";
		String tplcnt = adService.requestCustomerJs(tplpath);
		
		InputStream in = IOUtils.toInputStream(tplcnt);
		
		//InputStream in = IOUtils.toInputStream("(function(a){alert(a)})('你好a')","UTF-8");
		IOUtils.copy(in, os);
		os.flush();
		os.close();
	} 
	
	
	@RequestMapping(value = "/SC/MZADX", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public void obtainScript5(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse respose) throws IOException {
		
		respose.setContentType("text/javascript");
		respose.setCharacterEncoding("UTF-8");
		
		OutputStream os =respose.getOutputStream();
		
		InputStream in = this.getClass().getResourceAsStream("/js/MZADX.js");
		
		//InputStream in = IOUtils.toInputStream("(function(a){alert(a)})('你好a')","UTF-8");
		
		IOUtils.copy(in, os);
		
		in.close();
		
		os.flush();
		os.close();
	}
	
	@RequestMapping(value = "/SC/YOUDAO", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public void obtainScript6(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse respose) throws IOException {
		
		respose.setContentType("text/javascript");
		respose.setCharacterEncoding("UTF-8");
				
		OutputStream os =respose.getOutputStream();
		
		InputStream in = this.getClass().getResourceAsStream("/js/YOUDAO.js");
		
		//InputStream in = IOUtils.toInputStream("(function(a){alert(a)})('你好a')","UTF-8");
		
		IOUtils.copy(in, os);
		
		in.close();
		
		os.flush();
		os.close();
	}
	
	@RequestMapping(value = "/ad/addAdInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String insertAd(Map<String, Object> model,Ad ad,HttpServletRequest request) throws SQLException{
		adService.addAd(ad);
		
		
		return null;
	}
	
	@RequestMapping(value = "/ad/queryAdList", produces = "text/html;charset=UTF-8")
	public void queryAdList(Map<String, Object> model,HttpServletRequest request){
		
		
	}

}
