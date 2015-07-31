package com.bplow.netconn.base.security;

import java.io.IOException;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;


@Service
public class Base64Encode {
	
	Logger log = LoggerFactory.getLogger(Base64Encode.class);
	
	public String encodeString(String str){
		StopWatch stopWatch = new Log4JStopWatch();
		stopWatch.start("encode");
		byte[] rt = Base64.encodeBase64(str.getBytes());
		log.info("base64 encode:"+new String(rt) );
		stopWatch.stop("encode");
		return new String(rt);
	}
	
	
	public String decodeString(String str) throws IOException{
		byte[] rt = Base64.decodeBase64(str.getBytes());
		X509EncodedKeySpec  x509ek  = new X509EncodedKeySpec(rt);
		log.info("base64 Dencode:"+IOUtils.toString(x509ek.getEncoded()) );
		BASE64Decoder   b64d = new  BASE64Decoder();
		byte[] rt2 = b64d.decodeBuffer(str);
		log.info("sun base64 Dencode:"+new String(rt2,"GBK") );
		return new String(rt);
	}

}
