package com.bplow.netconn.base.dao.pagination;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;
import com.bplow.netconn.base.utils.JsonStructrueData;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SimplePagination implements Serializable, IPagination{

	public static final long serialVersionUID = 20080219L;
    private int firstResult;
    private int maxResults;
    private List results;
    private int allCount;
    private String querySQL;
    private String queryCountSQL;
    private int curPageIndex;
    private int allPageCount;
    
	
	public SimplePagination(int firstResult, int maxResults)
    {
        this.firstResult = firstResult;
        this.maxResults = maxResults;
    }

    public int getAllPageCount()
    {
        if(allCount == 0 || maxResults == 0)
            return 0;
        else
            return allCount / maxResults + (allCount % maxResults <= 0 ? 0 : 1);
    }

    public int getCurPageCount()
    {
        if(results != null)
            return results.size();
        else
            return 0;
    }

    public int getCurPageIndex()
    {
        int i = firstResult/maxResults;
        return i+1;
    }

    public int getAllCount()
    {
        return allCount;
    }

    public int getFirstResult()
    {
        return firstResult;
    }

    public int getMaxResults()
    {
        return maxResults;
    }

    public void setResults(List results)
    {
        this.results = results;
    }

    public List getResults()
    {
        return results;
    }

    public void setAllCount(int allCount)
    {
        this.allCount = allCount;
    }

    public void setFirstResult(int firstResult)
    {
        this.firstResult = firstResult;
    }

    public void setMaxResults(int maxResults)
    {
        this.maxResults = maxResults;
    }

	public String getQuerySQL() {
		return querySQL;
	}

	public void setQuerySQL(String querySQL) {
		this.querySQL = querySQL;
	}

	public String getQueryCountSQL() {
		return queryCountSQL;
	}

	public void setQueryCountSQL(String queryCountSQL) {
		this.queryCountSQL = queryCountSQL;
	}

	public void setCurPageIndex(int curPageIndex) {
		this.curPageIndex = curPageIndex;
	}

	public void setAllPageCount(int allPageCount) {
		this.allPageCount = allPageCount;
	}
public String getJsonByList() throws JsonGenerationException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        mapper.writeValue(sw, new JsonStructrueData(this.results,this.allCount));
        
        return sw.toString();
		
	}
	
	public String getJsonTreeByList() throws JsonGenerationException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
		
        mapper.writeValue(sw, this.results.toArray());
        
        return sw.toString();
		
	}
    
}
