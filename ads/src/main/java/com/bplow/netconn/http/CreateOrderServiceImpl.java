package com.bplow.netconn.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bplow.netconn.io.ProcessIoService;

public class CreateOrderServiceImpl {
	private static Logger logger = LoggerFactory.getLogger(CreateOrderServiceImpl.class);
	
	private ProcessIoService processIo;
	
	private String loginUrl ;
	private String createUrlStable ;
	public final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.151 Safari/535.19";
	
	public void createOrder(){
		
		CookieSpecProvider easySpecProvider = new CookieSpecProvider() {
			public CookieSpec create(HttpContext context) {
				return new BrowserCompatSpec() {
					@Override
					public void validate(Cookie cookie, CookieOrigin origin)
							throws MalformedCookieException {
						// Oh, I am easy
					}
				};
			}

		};
		RequestConfig requestConfig = RequestConfig.custom()
				.setCookieSpec("easy").setSocketTimeout(10000)
				.setConnectTimeout(10000).build();
		
		Registry<CookieSpecProvider> r = RegistryBuilder
				.<CookieSpecProvider> create()
				.register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
				.register(CookieSpecs.BROWSER_COMPATIBILITY,
				new BrowserCompatSpecFactory())
				.register("easy", easySpecProvider).build();
		
		LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom().setUserAgent(USER_AGENT)
				.setDefaultCookieSpecRegistry(r)
				.setDefaultRequestConfig(requestConfig)
				.setRedirectStrategy(redirectStrategy)
                .setDefaultCookieStore(cookieStore)
                .build();
		
		
		/**
		 * 第一次请求获取session
		 */
		HttpGet httpget = new HttpGet(loginUrl);
        CloseableHttpResponse response1 =null;
		try {
			response1 = httpclient.execute(httpget);
			
			HttpEntity entity = response1.getEntity();
			//processIo.printToConsole(entity.getContent());
			
			long len = entity.getContentLength();
	        if (len != -1 && len < 2048) {
	            System.out.println(EntityUtils.toString(entity));
	        } else {
	        }
	        logger.info("Login form get: {}",response1.getStatusLine());
	        EntityUtils.consume(entity);
	        
	        HeaderIterator it = response1.headerIterator("Set-Cookie");

	        while (it.hasNext()) {
	            System.out.println(it.next());
	        }
	        
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				response1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
        

        

        System.out.println("Initial set of cookies:");
        List<Cookie> cookies = cookieStore.getCookies();
        if (cookies.isEmpty()) {
            System.out.println("None");
        } else {
            for (int i = 0; i < cookies.size(); i++) {
                System.out.println("- " + cookies.get(i).toString());
            }
        }
        
        
        
        /**
         * 执行第二次请求
         */
        
        HttpClientContext context = HttpClientContext.create();
        List<URI> redirectLocations = null;
        
        HttpPost httpPost = new HttpPost(createUrlStable);
        
        
        httpPost.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
        		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        		httpPost.setHeader("Cache-Control", "max-age=0");
        		httpPost.setHeader("Connection", "keep-alive");
        		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        		httpPost.setHeader(
        				"User-Agent",
        				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36");
        
        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        
        /*Map map = processIo.parseParam();
		for(Iterator<Map.Entry<String, String>> entry = map.entrySet().iterator();entry.hasNext();){
			Map.Entry<String,String> ent = entry.next();
			nvps.add(new BasicNameValuePair(ent.getKey(),ent.getValue()));
		}*/
        
        String str = processIo.getParamStr("/requestparam/reqStable.txt");
        StringEntity strentity = new StringEntity(str,
                ContentType.create("plain/text", Consts.UTF_8));
        strentity.setChunked(true);
        try {
			httpPost.setEntity(strentity);
			CloseableHttpResponse response2 = httpclient.execute(httpPost,context);
			
			// 获取所有的重定向位置
			
			HttpEntity entity2 = response2.getEntity();
			redirectLocations = context.getRedirectLocations();
			if(redirectLocations!=null){
				for(URI uri : redirectLocations){
					System.out.println("|\nv\n" + uri.toASCIIString());
				}
			}
			
			System.out.println("第一次post请求status:" + response2.getStatusLine());
			
			Header head [] =response2.getAllHeaders();
			for(Header hed : head){
				//System.out.println(hed.getName()+"----"+hed.getValue());
			}
			
			processIo.printToConsole(entity2.getContent());
			long len = entity2.getContentLength();
	        if (len != -1 && len < 2048) {
	            System.out.println(EntityUtils.toString(entity2));
	        } else {
	        }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

        
        
        try {
        	StringEntity strentity2 = new StringEntity(str,
                    ContentType.create("plain/text", Consts.UTF_8));
            strentity.setChunked(true);
            httpPost.setEntity(strentity2);
			CloseableHttpResponse response3 = httpclient.execute(httpPost,context);
			
			logger.info("第二次post请求status：{}",response3.getStatusLine());
			processIo.printToConsole(response3.getEntity().getContent());
			redirectLocations = context.getRedirectLocations();
			if(redirectLocations!=null){
				for(URI uri : redirectLocations){
					System.out.println("|\nv\n" + uri.toASCIIString());
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public void setProcessIo(ProcessIoService processIo) {
		this.processIo = processIo;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public void setCreateUrlStable(String createUrlStable) {
		this.createUrlStable = createUrlStable;
	}

	
	
	
}
