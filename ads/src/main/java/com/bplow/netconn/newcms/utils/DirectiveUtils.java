package com.bplow.netconn.newcms.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;

public class DirectiveUtils {

	public static Map<String, TemplateModel> addParamsToVariable(Environment paramEnvironment, Map<String, TemplateModel> paramMap)
		    throws TemplateException
		  {
		    HashMap localHashMap = new HashMap();
		    if (paramMap.size() <= 0) {
		      return localHashMap;
		    }
		    Set localSet = paramMap.entrySet();
		    Iterator localIterator = localSet.iterator();
		    while (localIterator.hasNext())
		    {
		      Map.Entry localEntry = (Map.Entry)localIterator.next();
		      String str = (String)localEntry.getKey();
		      TemplateModel localTemplateModel = paramEnvironment.getVariable(str);
		      if (localTemplateModel != null) {
		        localHashMap.put(str, localTemplateModel);
		      }
		      paramEnvironment.setVariable(str, (TemplateModel)localEntry.getValue());
		    }
		    return localHashMap;
		  }
		  
		  public static void removeParamsFromVariable(Environment paramEnvironment, Map<String, TemplateModel> paramMap1, Map<String, TemplateModel> paramMap2)
		    throws TemplateException
		  {
		    if (paramMap1.size() <= 0) {
		      return;
		    }
		    Iterator localIterator = paramMap1.keySet().iterator();
		    while (localIterator.hasNext())
		    {
		      String str = (String)localIterator.next();
		      paramEnvironment.setVariable(str, (TemplateModel)paramMap2.get(str));
		    }
		  }
		  
		  public static boolean getBoolean(TemplateScalarModel paramTemplateScalarModel)
		    throws TemplateModelException
		  {
		    return "1".equals(paramTemplateScalarModel.getAsString());
		  }
		  
		  public static String getString(String paramString, Map<String, TemplateModel> paramMap)
		    throws TemplateException
		  {
		    TemplateModel localTemplateModel = (TemplateModel)paramMap.get(paramString);
		    if (localTemplateModel == null) {
		      return null;
		    }
		    if ((localTemplateModel instanceof TemplateScalarModel)) {
		      return ((TemplateScalarModel)localTemplateModel).getAsString();
		    }
		    if ((localTemplateModel instanceof TemplateNumberModel)) {
		      return ((TemplateNumberModel)localTemplateModel).getAsNumber().toString();
		    }
		    throw new MustStringException(paramString);
		  }
		  
		  public static Long getLong(String paramString, Map<String, TemplateModel> paramMap)
		    throws TemplateException
		  {
		    TemplateModel localTemplateModel = (TemplateModel)paramMap.get(paramString);
		    if (localTemplateModel == null) {
		      return null;
		    }
		    if ((localTemplateModel instanceof TemplateScalarModel))
		    {
		      String str = ((TemplateScalarModel)localTemplateModel).getAsString();
		      if (StringUtils.isBlank(str)) {
		        return null;
		      }
		      try
		      {
		        return Long.valueOf(Long.parseLong(str));
		      }
		      catch (NumberFormatException localNumberFormatException)
		      {
		        throw new MustNumberException(paramString);
		      }
		    }
		    if ((localTemplateModel instanceof TemplateNumberModel)) {
		      return Long.valueOf(((TemplateNumberModel)localTemplateModel).getAsNumber().longValue());
		    }
		    throw new MustNumberException(paramString);
		  }
		  
		  public static Integer getInt(String paramString, Map<String, TemplateModel> paramMap)
		    throws TemplateException
		  {
		    TemplateModel localTemplateModel = (TemplateModel)paramMap.get(paramString);
		    if (localTemplateModel == null) {
		      return null;
		    }
		    if ((localTemplateModel instanceof TemplateScalarModel))
		    {
		      String str = ((TemplateScalarModel)localTemplateModel).getAsString();
		      if (StringUtils.isBlank(str)) {
		        return null;
		      }
		      try
		      {
		        return Integer.valueOf(Integer.parseInt(str));
		      }
		      catch (NumberFormatException localNumberFormatException)
		      {
		        throw new MustNumberException(paramString);
		      }
		    }
		    if ((localTemplateModel instanceof TemplateNumberModel)) {
		      return Integer.valueOf(((TemplateNumberModel)localTemplateModel).getAsNumber().intValue());
		    }
		    throw new MustNumberException(paramString);
		  }
		  
		  public static Date getDate(String paramString, Map<String, TemplateModel> paramMap)
		    throws TemplateException
		  {
		    TemplateModel localTemplateModel = (TemplateModel)paramMap.get(paramString);
		    if (localTemplateModel == null) {
		      return null;
		    }
		    if ((localTemplateModel instanceof TemplateDateModel)) {
		      return ((TemplateDateModel)localTemplateModel).getAsDate();
		    }
		    if ((localTemplateModel instanceof TemplateScalarModel))
		    {
		      DateTypeEditor localDateTypeEditor = new DateTypeEditor();
		      localDateTypeEditor.setAsText(((TemplateScalarModel)localTemplateModel).getAsString());
		      return (Date)localDateTypeEditor.getValue();
		    }
		    throw new MustDateException(paramString);
		  }
	
}
