package com.bplow.netconn.query.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.bplow.netconn.base.chart.HgChartConf;
import com.bplow.netconn.base.chart.Serie;
import com.bplow.netconn.base.chart.XAxis;
import com.bplow.netconn.base.json.JsonHelper;
import com.bplow.netconn.base.tmpl.VelocityHelper;
import com.bplow.netconn.base.utils.DateUtil;
import com.bplow.netconn.base.utils.TraceNoGenerater;
import com.bplow.netconn.query.dao.AdDao;
import com.bplow.netconn.query.dao.entity.Ad;
import com.bplow.netconn.query.dao.entity.CustomerData;
import com.bplow.netconn.query.module.ReqForm;
import com.bplow.netconn.query.service.Adservice;
import com.bplow.netconn.query.service.LogConfigService;
import com.bplow.netconn.query.service.TmplCntCacheService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service("adService")
//@Transactional
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
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	@Autowired
	private LogConfigService logConfigService;
	
	@Value("${basecdnurl}")
	private String  basecdnurl ;//http://115.28.240.191:8080/ads/SC
	@Value("${baseurl}")
	private String  baseurl ;//http://115.28.240.191:8080/ads/SC
	
	private @Value("${domainName}") String domainName ;
	
	private int  MY_MINIMUM_COLUMN_COUNT = 6;
	
	@Override
	public Ad getAdById(String id) {
		
		
		
		return null;
	}

	@Override
	public String obtionBaseScript(ReqForm reqForm,String tplPath) {
		if (logConfigService.enableLog()) {
			logger.info("获取基础JS:{}", reqForm);
		}
		//String traceNo = traceNoGenerater.getNo();
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
				if(StringUtils.isNotBlank(customIdStr)){
					customId = Integer.parseInt(customIdStr);
				}
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
		Map<String,String> map = new HashMap<String,String>();
		
		if(adId.contains("_")){
			String paraArray [] = adId.split("_");
			for(int i = 0;i < paraArray.length;i++){
				map.put("A"+i, paraArray[i].replace("$","_"));
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
		if(logConfigService.enableLog()){
			logger.info("customName:{},property：{},executeJS:{}",adName,property,str);
			log.info("REQUEST:[{},{},{}]",traceNo,adName,property);
		}
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
		//Row row = sheet.getRow(0);
		//int num = sheet.getPhysicalNumberOfRows();
		int rowStart  = sheet.getFirstRowNum();
		int rowEnd   = sheet.getLastRowNum();
		
		final List<CustomerData> data = new ArrayList<CustomerData>();
		for (int rowNum = rowStart+1; rowNum < rowEnd; rowNum++) {
			Row r = sheet.getRow(rowNum);
			if (r == null) {
				continue;
			}

			int lastColumn = Math.max(r.getLastCellNum(),
					MY_MINIMUM_COLUMN_COUNT);
			CustomerData customertmp = new CustomerData();
			customertmp.setCustomerId(customerData.getCustomerId());

			for (int cn = 0; cn < lastColumn; cn++) {
				Cell c = r.getCell(cn, Row.RETURN_BLANK_AS_NULL);
				if(cn== 0 && null != c){
					customertmp.setUploadData(c.getDateCellValue());
				}if(cn== 1 && null != c){
					customertmp.setCustomerName(c.getStringCellValue());
				}if(cn== 2 && null != c){
					customertmp.setAdAddr(c.getStringCellValue());
				}if(cn== 3 && null != c){
					customertmp.setShowNum( c.getNumericCellValue());
				}if(cn== 4 && null != c){
					customertmp.setClickNum(c.getNumericCellValue());
				}if(cn== 5 && null != c){
					customertmp.setIncome(c.getNumericCellValue());
				}
			}
			data.add(customertmp);
		}
		wb.close();
		fs.close();
		// 批量插入数据库
		this.transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus status) {
				try {
					adDao.batchInsertCustomerData(data);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return true;
			}
		});

	}

	@Override
	public String queryCustomerDataForChar(CustomerData customerData)
			throws SQLException, JsonProcessingException, UnsupportedEncodingException {
		
		List<CustomerData> list = adDao.queryCustomerData(customerData);
		Map<String,CustomerData> datamap = new HashMap<String,CustomerData>();
		Set<String> xset = new HashSet<String>();
		Set<String> yset = new HashSet<String>();
		
		HgChartConf hgChartConf =new HgChartConf();
		
		
		XAxis xAxis = new XAxis();
		
		hgChartConf.setxAxis(xAxis);
		for(CustomerData cdata : list){
			xset.add(DateUtil.datetoStr(cdata.getUploadData()));//获取x轴
			yset.add(cdata.getCustomerName());//获取y轴
			datamap.put(cdata.getCustomerName()+DateUtil.datetoStr(cdata.getUploadData()), cdata);
		}
		final TreeSet<String> xsetSort = new TreeSet(xset);
		xsetSort.comparator();
		xAxis.setCategories(xsetSort.toArray());//设置x轴
		hgChartConf.setSeries(new Serie[yset.size()]);//多少系
		
		int seriesnum = 0;
		for(String yname : yset){//遍历系
			Serie xaxis = new Serie(yname,new Object[xset.size()]);//x系
			int i = 0;
			for(String xname : xsetSort){//填充系的数据
				CustomerData tmpCustomer = datamap.get(yname+xname);
				if(null != tmpCustomer){
					xaxis.getData()[i] = tmpCustomer.getIncome();
				}
				i ++;
			}
			hgChartConf.getSeries()[seriesnum] = xaxis;
			seriesnum++;
		}//遍历系统
		
		String chartjsondata = JsonHelper.convertToStr(hgChartConf);

	    log.info("json chart data:[{}]",chartjsondata);
		return chartjsondata;
	}

	@Override
	public String queryCustomerData(CustomerData customerData)
			throws SQLException, JsonProcessingException, UnsupportedEncodingException {
		List list = adDao.queryCustomerData(customerData);
		Map map = new HashMap();
		map.put("dataroot", list);
		
		return JsonHelper.convertToStr(map);
	}
	
	
	@Override
	public void exportCustomerData(CustomerData customerData,OutputStream out) throws SQLException, Exception {
		List<CustomerData> list = adDao.queryCustomerData(customerData);
		
		Workbook wb = new HSSFWorkbook();
	    //Workbook wb = new XSSFWorkbook();
	    CreationHelper createHelper = wb.getCreationHelper();
	    Sheet sheet = wb.createSheet("new sheet");

	    // Create a row and put some cells in it. Rows are 0 based.
	    Row row = sheet.createRow(0);
	    String tableheader[] =new String[]{"时间","媒体","广告位","展示量","点击","收益"};
	    CellStyle cellStyle = wb.createCellStyle();
	    cellStyle.setDataFormat(
	        createHelper.createDataFormat().getFormat("yyyy/m/d"));
	    //设置表头
	    for(int i = 0;i < tableheader.length;i++){
	    	Cell tmpcell = row.createCell(i);
	    	tmpcell.setCellValue(tableheader[i]);
	    }
	    int startrow = 1;
	    for(CustomerData cData : list){
	    	Row _row = sheet.createRow(startrow);
	    	for(int j=0;j<tableheader.length;j++ ){
	    		Cell tmpcell = _row.createCell(j);
	    		if(j == 0){
	    			tmpcell.setCellStyle(cellStyle);
	    			tmpcell.setCellValue(cData.getUploadData() );
	    		}else if(j == 1){
	    			tmpcell.setCellValue(cData.getCustomerName());
	    		}else if(j == 2){
	    			tmpcell.setCellValue(cData.getAdAddr());
	    		}else if(j == 3){
	    			tmpcell.setCellValue(cData.getShowNum());
	    		}else if(j == 4){
	    			tmpcell.setCellValue(cData.getClickNum());
	    		}else if(j == 5){
	    			tmpcell.setCellValue(cData.getIncome());
	    		}
	    	}
	    	startrow ++;
	    }
	    
		
	    wb.write(out);
	    wb.close();
	    //out.flush();
	    out.close();
	}
	
	

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public String getBasecdnurl() {
		return basecdnurl;
	}

	public void setBasecdnurl(String basecdnurl) {
		this.basecdnurl = basecdnurl;
	}

	public String getBaseurl() {
		return baseurl;
	}

	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}



	
	
	
}
