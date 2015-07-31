package com.bplow.netconn.base.autocreatedao;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.SAXException;

import com.bplow.netconn.base.autocreatedao.model.XmlTable;

public class ProductTableConsumerFactory<T> {
	
	public String parseRuleFilePath ;
	public String originTableXmlFilePath;
	
	public T createTable(T obj) throws IOException, SAXException{
		URL rules = getClass().getResource("/table/digest/user-rules.xml");
		Digester digester = DigesterLoader.createDigester(rules);
		XmlTable xmlTable = new XmlTable();
		digester.push(xmlTable);
		
		InputStream is = this.getClass().getResourceAsStream("/table/user.xml");
		
		digester.parse(is);
		
		return obj;
	}
	
	
	
	
	public static void main(String[] args) throws IOException, SAXException {
		ProductTableConsumerFactory fa = new ProductTableConsumerFactory();
		fa.createTable(null);
	}

	public String getParseRuleFilePath() {
		return parseRuleFilePath;
	}

	public void setParseRuleFilePath(String parseRuleFilePath) {
		this.parseRuleFilePath = parseRuleFilePath;
	}

	public String getOriginTableXmlFilePath() {
		return originTableXmlFilePath;
	}

	public void setOriginTableXmlFilePath(String originTableXmlFilePath) {
		this.originTableXmlFilePath = originTableXmlFilePath;
	}
	
	

}
