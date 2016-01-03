package com.bplow.netconn.base.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonStructureDataHelp {
	
	public List data;
	public int itemNum;//总条数
	public int pageNum;//当前页
	public Object obj;
	
	
	public JsonStructureDataHelp(List cntItemarg){
		this.data = cntItemarg;
		this.itemNum = cntItemarg.size();
	}
	
	public JsonStructureDataHelp(List cntItemarg,int allnum){
		this.data = cntItemarg;
		this.itemNum = allnum;
	}
	public JsonStructureDataHelp(List cntItemarg,int allnum,int pageNum){
		this.data = cntItemarg;
		this.itemNum = allnum;
		this.pageNum = pageNum;
	}
	public JsonStructureDataHelp(Object object){
		this.obj = object;
	}
	
	public String getJsonByList() throws JsonGenerationException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        //mapper.writeValue(sw, new JsonStructrueData(this.cntItem,this.itemNum));
        String str = mapper.writeValueAsString(new JsonStructrueData(this.data,this.itemNum,this.pageNum));
        sw.flush();
        sw.close();
        return str;
	}
	
	public String getJsonTreeByList() throws JsonGenerationException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
		
        mapper.writeValue(sw, this.data.toArray());
        String str = sw.toString();
        sw.flush();
        sw.close();
        return str;
		
	}
	
	public String getObjectToJsonString()throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        mapper.writeValue(sw, this.obj);
        String str = sw.toString();
        sw.flush();
        sw.close();
        return str;
	}
	
	
	

}


