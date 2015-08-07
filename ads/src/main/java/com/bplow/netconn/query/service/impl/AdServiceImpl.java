package com.bplow.netconn.query.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bplow.netconn.base.tmpl.VelocityHelper;
import com.bplow.netconn.base.utils.TraceNoGenerater;
import com.bplow.netconn.query.dao.AdDao;
import com.bplow.netconn.query.dao.entity.Ad;
import com.bplow.netconn.query.dao.entity.CustomerData;
import com.bplow.netconn.query.module.ReqForm;
import com.bplow.netconn.query.service.Adservice;
import com.bplow.netconn.query.service.TmplCntCacheService;

@Service("adService")
@Transactional
public class AdServiceImpl implements Adservice{

	private static Logger logger = LoggerFactory.getLogger(AdServiceImpl.class);
	
	private static Logger log    =  LoggerFactory.getLogger("common-digest");
	
	@Autowired
	TraceNoGenerater traceNoGenerater;
	@Autowired
	TmplCntCacheService tmplCntCacheService;
	
	@Autowired
	private AdDao adDao;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private VelocityHelper velocityHelper;
	
	private final String  basecdnurl ="http://115.28.240.191/ads/SC";//http://115.28.240.191:8080/ads/SC
	
	private final String  baseurl ="http://115.28.240.191/ads/SC";//http://115.28.240.191:8080/ads/SC
	
	private int  MY_MINIMUM_COLUMN_COUNT = 6;
	
	@Override
	public Ad getAdById(String id) {
		
		
		
		return null;
	}

	@Override
	public String obtionBaseScript(ReqForm reqForm,String tplPath) {
		logger.info("获取基础JS:{}",reqForm);
		String traceNo = traceNoGenerater.getNo();
		Map map = new HashMap();
		map.put("basecdnurl", basecdnurl);
		map.put("baseurl", baseurl);
		map.put("ext", reqForm.getExt());
		map.put("id",reqForm.getId());
		map.put("version", 7);
		map.put("target", "AD_Sellbuyads");
		String str = null;
		String tplcnt = null;
		Object tmplcntObj = tmplCntCacheService.getTmplByKey(tplPath);
		if(tmplcntObj != null){
			tplcnt = (String)tmplcntObj;
		}
		try {
			str = velocityHelper.renderStr(map, tplcnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public String executeMethod(ReqForm reqForm,OutputStream os) throws SQLException {
		String traceNo = traceNoGenerater.getNo();
		String id     = reqForm.getId();
		String exeNum = reqForm.getC();
		String cnidx  = reqForm.getCnidx();
		String ext    = reqForm.getExt();
		String adId   = null;//广告id
		int customId = 0;//客户id
		String adName = "";
		String property ="";
		String [] adArray = ext.split("\\|");
		String [] customerIdArray = id.split("_");
		
		
		
		if(StringUtils.isNotBlank(cnidx)){
			int cnidxnum = Integer.parseInt(cnidx);
			if(cnidxnum <= adArray.length){
				adId = adArray[cnidxnum-1];
			}
			if(cnidxnum <= customerIdArray.length){
				String customIdStr = customerIdArray[cnidxnum-1];
				customId = Integer.parseInt(customIdStr);
			}
		}
		
		if(0 == customId){
			throw new IllegalArgumentException("没有可展示的客户！");
		}
		
		//Ad ad = adDao.queryAd(customId);
		Ad ad = (Ad)tmplCntCacheService.getTmplByKey(customId+"");
		
		adName = ad.getAdId();//客户id
		property = ad.getAdProperty();//属性
		
		//property.replaceAll("_id", adId);
		Map map = new HashMap();
		
		if(adId.contains("_")){
			String paraArray [] = adId.split("_");
			for(int i = 0;i < paraArray.length;i++){
				map.put("A"+i, paraArray[i]);
			}
		}else{
			map.put("id", adId);
		}
		
		property = velocityHelper.renderStr(map, property);
		
		/*if("1".equals(cnidx)){
			adName = "MZADX";
			property = "{'l':'6298'}";
		}else{
			adName = "YOUDAO";
			property = "{'slotid':'1387'}";
		}*/
		
		String str = "(function (win, doc) {"
				+ exeNum + "(0,'" + adName
				+ "',"+property+")})(window, document);";
		logger.info("customName:{},property：{},executeJS:{}",adName,property,str);
		log.info("REQUEST:[{},{},{}]",traceNo,adName,property);
		return str;
	}

	@Override
	public String requestCustomerJs(String tplPath) {
		Object tmplcntObj = tmplCntCacheService.getTmplByKey(tplPath);
		String tplcnt = null;
		
		if(tmplcntObj != null){
			tplcnt = (String)tmplcntObj;
		}
		
		return tplcnt;
	}

	@Override
	public String addAd(Ad ad) throws SQLException {
		adDao.insertAd(ad);
		return "ok";
	}

	@Override
	public List queryAdList(Ad ad) throws SQLException {
		
		List list = adDao.queryAdList(ad);
		
		return list;
	}

	@Override
	public void updateAd(Ad ad) throws SQLException {
		
	}
	
	@Override
	public void delAd(Ad ad) throws SQLException {
		adDao.delAd(ad.getId());
	}

	@Override
	public void batchAddCustomerData(InputStream in, CustomerData customerData)
			throws SQLException, IOException {
		//读取excel数据
		NPOIFSFileSystem fs = new NPOIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), true);
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(0);
		int num = sheet.getPhysicalNumberOfRows();
		int rowStart  = sheet.getFirstRowNum();
		int rowEnd   = sheet.getLastRowNum();
		
		List data = new ArrayList();
		for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
			Row r = sheet.getRow(rowNum);
			if (r == null) {
				continue;
			}

			int lastColumn = Math.max(r.getLastCellNum(),
					MY_MINIMUM_COLUMN_COUNT);
			CustomerData customertmp = new CustomerData();

			for (int cn = 0; cn < lastColumn; cn++) {
				Cell c = r.getCell(cn, Row.RETURN_BLANK_AS_NULL);
				if (c == null) {
					customertmp.setCustomerName(c.getStringCellValue());
				} else {
					
				}
			}
			data.add(customertmp);
		}
		
		//批量插入数据库
		adDao.batchInsertCustomerData(data);
		
	}

	@Override
	public String queryCustomerDataForChar(CustomerData customerData)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryCustomerData(CustomerData customerData)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
