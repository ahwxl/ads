package com.bplow.netconn.base.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
 * @desc 
 * @author wangxiaolei
 * @date 2016年1月3日 下午5:40:22
 */
public class CustomDateSerializer extends JsonSerializer<Date>{

	@Override
	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        String formattedDate = formatter.format(value);  
        jgen.writeString(formattedDate);  
		
	}

}
