package com.bplow.netconn.base.security;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class jksToBase64Encode {
	
	Logger log = LoggerFactory.getLogger(jksToBase64Encode.class);
	
	public void jksToBase64() throws IOException{
		InputStream in = this.getClass().getResourceAsStream("/zhongan.jks");
		
		String certContainer = null;
        try {
            byte[] bInstCert = new byte[in.available()];
            in.read(bInstCert);
            in.close();
            certContainer = new String(Base64.encodeBase64(bInstCert));
            File file = new File("D:/zhongan_base64.jks");
            file.createNewFile();
            FileUtils.writeStringToFile(file, certContainer);
        } catch (IOException e) {
        	log.error("【证书上传】IO异常", e);
        } finally {
            in.close();
        }
		
		
	}
	
	

}
