package com.bplow.netconn.http;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

public class ProcessHttpService {
	
	
	public HttpGet createHttpGet(){
		
		
		
		return null;
	}
	
	
	public HttpPost createHttpPost(String url){
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpPost.setHeader("Cache-Control", "max-age=0");
		httpPost.setHeader("Connection", "keep-alive");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36");
		
		return httpPost;
	}

}
