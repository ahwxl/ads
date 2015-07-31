package com.bplow.netconn.base.autocreatedao.model;

public enum MysqlToJavaTypeEnum {
	
	INT("INT","int"),
	BIGINT("INT","int"),
	DATE("DATE","Date"),
	TIMESTAMP("TIMESTAMP","Date"),
	VARCHAR("VARCHAR","String");
	
	private String code;
	
	private String javaType;

	private MysqlToJavaTypeEnum(String code, String javaType) {
		this.code = code;
		this.javaType = javaType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	
	

}
