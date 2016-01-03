package com.bplow.netconn.base.dao.pagination;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface IPagination {
	
	public abstract int getAllCount();

    public abstract void setAllCount(int i);

    public abstract int getAllPageCount();

    public abstract int getCurPageCount();

    public abstract int getCurPageIndex();

    public abstract List getResults();

    public abstract void setResults(List list);

    public abstract void setFirstResult(int i);

    public abstract int getFirstResult();

    public abstract void setMaxResults(int i);

    public abstract int getMaxResults();
    
    public static final int DEFAUL_PAGERESULTCOUNT = 12;
    
    public String getQuerySQL();

	public void setQuerySQL(String querySQL);

	public String getQueryCountSQL();

	public void setQueryCountSQL(String queryCountSQL);
	
	public void setCurPageIndex(int curPageIndex);

	public void setAllPageCount(int allPageCount);
	
	public String getJsonByList()throws JsonGenerationException, JsonMappingException, IOException;
	
	public String getJsonTreeByList()throws JsonGenerationException, JsonMappingException, IOException;

}
