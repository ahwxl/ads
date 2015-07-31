package com.bplow.netconn.newcms.utils;

import freemarker.template.TemplateModelException;

public class MustStringException extends TemplateModelException{

	public MustStringException(String paramString)
	  {
	    super("The \"" + paramString + "\" parameter must be a string.");
	  }
}
