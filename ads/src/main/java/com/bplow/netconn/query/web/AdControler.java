package com.bplow.netconn.query.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bplow.netconn.base.json.JsonHelper;
import com.bplow.netconn.query.dao.entity.Ad;
import com.bplow.netconn.query.module.ReqForm;
import com.bplow.netconn.query.service.Adservice;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class AdControler {
	
	@Autowired
	private Adservice adService;
	
	private String domainName ="115.28.240.191";
	
	
	/**
	 * 客户列表
	 * @return
	 */
	@RequestMapping(value = "/ad/customer", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String adMain(){
		
		
		return "ad/customerList";
	}
	
	
	@RequestMapping(value = "/ad/querycustomerlist", method = RequestMethod.POST)
	@ResponseBody
	public String obtainScript(Map<String, Object> model,ReqForm reqForm,
			HttpServletRequest request, HttpServletResponse respose) throws SQLException, JsonProcessingException, UnsupportedEncodingException {
		Ad ad = new Ad();
		
		List list = adService.queryAdList(ad);
		Map map = new HashMap();
		map.put("dataroot", list);
		
		
		return JsonHelper.convertToStr(map);
	}
	@RequestMapping(value = "/ad/showad")
    public String showAd(Map<String, Object> model,ReqForm reqForm,
			HttpServletRequest request, HttpServletResponse respose){
    	
		String param = request.getParameter("ad_out_ta");
		String id    = request.getParameter("id");
		String ext   = request.getParameter("ext");
		model.put("id", id);
		model.put("ext", ext);
		//domainName = "localhost:8080";
		model.put("domainName", domainName);
    	
    	return "ad/showAd";
    }
	
	@RequestMapping(value = "/ad/addAdPage")
    public String showAdAddPage(Map<String, Object> model,ReqForm reqForm,
			HttpServletRequest request, HttpServletResponse respose){
		
		return "ad/addCustomer";
	}
	
	@RequestMapping(value = "/ad/addAdAction", produces = "text/html;charset=UTF-8")
	@ResponseBody
    public String showAdAddAction(Map<String, Object> model,Ad reqForm,
			HttpServletRequest request, HttpServletResponse respose) throws SQLException{
		
		adService.addAd(reqForm);
		
		return "ad/addAdPage";
	}
	
	@RequestMapping(value = "/ad/delAdAction", produces = "text/html;charset=UTF-8")
	@ResponseBody
    public String delAdAction(Map<String, Object> model,Ad reqForm,
			HttpServletRequest request, HttpServletResponse respose) throws SQLException{
		
		adService.delAd(reqForm);
		
		return "ok";
	}
	
	@RequestMapping(value = "/ad/updateAdAction", produces = "text/html;charset=UTF-8")
	@ResponseBody
    public String updateAdAction(Map<String, Object> model,Ad reqForm,
			HttpServletRequest request, HttpServletResponse respose) throws SQLException{
		
		adService.updateAd(reqForm);
		
		return "ok";
	}
	
	

}
