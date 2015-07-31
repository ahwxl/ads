package com.bplow.netconn.newcms.utils;

import freemarker.template.TemplateModelException;

public class MustDateException extends TemplateModelException{

	public MustDateException(String paramString)
	  {
	    super("The \"" + paramString + "\" parameter must be a date.");
	  }
}
