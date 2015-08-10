package com.bplow.netconn.base.dao;

import java.util.ArrayList;
import java.util.List;

public class HQLEntity {
	private StringBuffer sbf = null;
	private List paramList = null;
	
	public HQLEntity() {
		super();
		this.sbf = new StringBuffer();
		this.paramList = new ArrayList(5);
	}
	
	public HQLEntity(String basssql) {
		super();
		this.sbf = new StringBuffer(basssql);
		this.paramList = new ArrayList(5);
	}
	
	public HQLEntity(StringBuffer sbf, List paramList) {
		super();
		this.sbf = sbf;
		this.paramList = paramList;
	}

	public void append(String hql){
		sbf.append(hql);
	}
	
	public void append(String hql,Object obj){
		sbf.append(hql);
		paramList.add(obj);
	}
	
	public String gethqlSql(){
		return sbf.toString();
	}
	
	public List getParamList(){
		return this.paramList;
	}
	
	public Object[] getParamObjArray(){
		return this.paramList.toArray();
	}
	
	public int[] paramTypeArray(){
		int[] paramType = new int[this.paramList.size()];
		int i = 0;
		for(Object obj : this.paramList.toArray()){
			if(obj instanceof String){
				paramType[i] = java.sql.Types.VARCHAR;
			}else if(obj instanceof java.sql.Date){
				paramType[i] = java.sql.Types.DATE;
			}else if(obj instanceof java.util.Date){
				paramType[i] = java.sql.Types.DATE;
			}else if(obj instanceof java.lang.Number){
				paramType[i] = java.sql.Types.NUMERIC;
			}else if(obj instanceof java.lang.Double){
				paramType[i] = java.sql.Types.DOUBLE;
			}else if(obj instanceof java.math.BigDecimal){
				paramType[i] = java.sql.Types.DECIMAL;
			}else if(obj == null){
				paramType[i] = java.sql.Types.NULL;
			}
			i ++;
		}
				
		return paramType;
	}

}
