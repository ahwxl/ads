package com.bplow.netconn.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessIoService {
	
	private static Logger logger = LoggerFactory.getLogger(ProcessIoService.class);
	
	/**
	 * 打印控制台
	 */
	public void printToConsole(InputStream in){
        String chatset = System.getProperty("file.encoding");
        //System.setProperty("file.encoding", "GB2312");
        //System.out.println(chatset);
		OutputStream out = System.out;
		try {
			logger.info(IOUtils.toString(in, "GBK"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//logger.info(new String(in.));
		/*try {
			//IOUtils.copy(in, out);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	/**
	 * 处理已&号隔开的字符串
	 */
	public Map parseParam(){
		InputStream in = this.getClass().getResourceAsStream("/requestparam/reqStable.txt");
		Map<String ,String> map = new HashMap<String ,String>();
		try {
			String paramStr = IOUtils.toString(in);
			
			//System.out.println(paramStr);
			String[] paramArray = paramStr.split("&");
			for(String tmpstr : paramArray){
				System.out.println(tmpstr);
				if(StringUtils.isNotBlank(tmpstr)){
					String [] keyvalue = tmpstr.split("=");
					if(null != keyvalue && keyvalue.length == 2){
						map.put(keyvalue[0], keyvalue[1]);
					}else{
					    map.put(keyvalue[0], "");
					}
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 获取参数字符串
	 */
	public String getParamStr(String filePath){
		InputStream in = this.getClass().getResourceAsStream(filePath);
		String paramStr = null;
		try {
			paramStr = IOUtils.toString(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return paramStr;
	}
	
	/**
	 * 
	 * 读取cookie属性
	 */
	public String readByline(){
		
		
		return "";
	}

}
