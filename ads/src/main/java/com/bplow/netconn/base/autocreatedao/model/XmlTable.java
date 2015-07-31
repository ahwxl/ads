package com.bplow.netconn.base.autocreatedao.model;

import java.util.ArrayList;
import java.util.List;

public class XmlTable {
	
	public String name;
	public String desc;
	public String indexName;
	public List<XmlMethod> methods = new ArrayList<XmlMethod>();
	
	public void add(Object md){
		this.methods.add((XmlMethod)md);
	}
	
	public XmlTable() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public List<XmlMethod> getMethods() {
		return methods;
	}
	public void setMethods(List<XmlMethod> methods) {
		this.methods = methods;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
