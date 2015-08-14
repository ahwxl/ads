package com.bplow.netconn.query.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bplow.netconn.base.json.JsonHelper;
import com.bplow.netconn.query.dao.entity.Ad;
import com.bplow.netconn.query.dao.entity.CustomerData;
import com.bplow.netconn.query.module.ReqForm;
import com.bplow.netconn.query.service.Adservice;
import com.bplow.netconn.systemmng.dao.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class AdControler {
	
	@Autowired
	private Adservice adService;
	
	@Value("${domainName}")
	private String domainName ;
	
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
	
	@RequestMapping(value = "/ad/uploadCustemFileAction", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String uploadFile(@RequestParam("file") MultipartFile file,
			CustomerData customer, Map<String, Object> model)
			throws IOException, SQLException {

		InputStream in = file.getInputStream();
		
		adService.batchAddCustomerData(in, customer);

		return "{success:true,info:'上传成功!'}";
	}
	
	@RequestMapping(value = "/ad/queryCustemDataAction", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryCustomerDataAction(HttpServletRequest request,CustomerData customerData, Map<String, Object> model) throws SQLException, JsonProcessingException, UnsupportedEncodingException{
		User loginUser = (User)request.getSession().getAttribute("lgu");
		if(null != loginUser){
			customerData.setLoginUserId(loginUser.getUserName());
			customerData.setOrganizeId(loginUser.getOrgnatizeId());
		}
		String jsonstr = adService.queryCustomerData(customerData);
		
		return jsonstr;
	}
	
	@RequestMapping(value = "/ad/queryCustemDataForChart", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryCustomerDataForChart(CustomerData customerData,
			Map<String, Object> model,HttpServletRequest request) throws SQLException, Exception, UnsupportedEncodingException {
		User loginUser = (User)request.getSession().getAttribute("lgu");
		if(null != loginUser){
			customerData.setLoginUserId(loginUser.getUserName());
			customerData.setOrganizeId(loginUser.getOrgnatizeId());
		}

		String chartjson = adService.queryCustomerDataForChar(customerData);

		return chartjson;
	}
	
	@RequestMapping(value = "/ad/queryCustemDataPage", produces = "text/html;charset=UTF-8")
	public String showCusterDataPage(){
		
		
		
		return "ad/customerDataView";
	}
	
	@RequestMapping(value = "/ad/uploadCustemDataPage", produces = "text/html;charset=UTF-8")
	public String uploadCusterDataPage(){
		
		
		
		return "ad/uploadCustomerData";
	}
	
	@RequestMapping(value = "/ad/exportCustemData", produces = "application/vnd.ms-excel;charset=utf-8")
	public void exportCusterDataPage(Map<String, Object> model,CustomerData customerData,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((UUID.randomUUID().toString().replace("-", "") + ".xls").getBytes(), "iso-8859-1"));
		OutputStream out =response.getOutputStream();
		adService.exportCustomerData(customerData,out);
	} 
	
	
	
	
	
	
	

}
