package com.bplow.netconn.base.tmpl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;


/**
 * 
 * @author wb-wangxiaolei.xl
 * @version $Id: VelocityHelper.java, v 0.1 2015年4月29日 上午10:18:17 wb-wangxiaolei.xl Exp $
 */
@Service
public class VelocityHelper implements InitializingBean{
	
	private static Logger logger = LoggerFactory.getLogger(VelocityHelper.class);
	
	String configPath ="/table/velocity/velocity.properties";
	VelocityEngine vEngine;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}
	
	public void init() throws Exception{
		vEngine = new VelocityEngine();
		vEngine.setProperty("input.encoding", "UTF-8");
		vEngine.setProperty("output.encoding", "UTF-8");
		Properties p = new Properties();
		InputStream in = this.getClass().getResourceAsStream(configPath);
        p.load( in );
		
        p.setProperty(Velocity.INPUT_ENCODING, "GBK");
        p.setProperty(Velocity.OUTPUT_ENCODING, "GBK");
        p.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
        p.setProperty("file.resource.loader.class", "com.bplow.netconn.base.tmpl.KindFileResourceLoader");
        p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "/table/vm/");
		vEngine.init(p);
		
	}
	
	public String renderTemplate(Map map) throws ParseErrorException,
			MethodInvocationException, ResourceNotFoundException, IOException {

		VelocityContext context = this.createContext(map);
		
		StringWriter writer = new StringWriter();
		Reader reader = new InputStreamReader(getClass().getResourceAsStream(
				"organize.vm"));
		vEngine.evaluate(context, writer, "test", reader);

		logger.debug("organize: " + writer.toString());

		return writer.toString();
	}
	
	public String renderTemplate(Map map, InputStream in)
			throws Exception {
		StringWriter writer = new StringWriter();
		VelocityContext context = this.createContext(map);
		//vEngine.evaluate(context, writer, "tmpl", in);
		vEngine.mergeTemplate("/welcome.vm", "UTF-8", context, writer);
		logger.error("cxt:{}",writer.toString());
		return writer.toString();
	}
	
	/**
	 * 
	 * @param map
	 * @param in
	 * @param out
	 * @return
	 * @throws Exception
	 */
	public String renderTemplate(Map map, InputStream in,OutputStream out)
			throws Exception {
		StringWriter writer = new StringWriter();
		//BufferedWriter bw = new BufferedWriter(writer);
		//OutputStreamWriter  opw = new OutputStreamWriter(out);
		VelocityContext context = this.createContext(map);
		vEngine.evaluate(context, writer, "tmpl", in);
		//vEngine.mergeTemplate("/welcome.vm", "UTF-8", context, writer);
		
		//vEngine.evaluate(context, opw, "tmpl", in);
		
		logger.error("获取base js:{}",writer.toString());
		return writer.toString();
	}
	
	
	
	public String renderTemplate(Map map, String tmplName) throws ResourceNotFoundException, ParseErrorException, Exception{
		StringWriter writer = new StringWriter();
		VelocityContext context = this.createContext(map);
		Template template = vEngine.getTemplate(tmplName);
		template.merge(context, writer);
		return writer.toString();
	}
	
	public String renderTemplate(Map map, String tmplName,Writer writer)
			throws Exception {
		//StringWriter writer = new StringWriter();
		VelocityContext context = this.createContext(map);
		//vEngine.evaluate(context, writer, "tmpl", in);
		vEngine.mergeTemplate(tmplName, "UTF-8", context, writer);
		logger.error("cxt:{}",writer.toString());
		return writer.toString();
	}
	
	public String renderStr(Map map, String tmplCnt){
		StringWriter writer = new StringWriter();
		VelocityContext context = this.createContext(map);
		vEngine.evaluate(context, writer, "tmplog", tmplCnt);
		
		return writer.toString();
	}
	
	
	
	private VelocityContext createContext(Map map){
		VelocityContext context = new VelocityContext();
		for(Iterator<Map.Entry<String ,Object>> itr = map.entrySet().iterator();itr.hasNext();){
			Map.Entry<String ,Object> ent = itr.next();
			context.put(ent.getKey(), ent.getValue());
		}
		
		return context;
	}


}
