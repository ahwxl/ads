package com.bplow.netconn.newcms.utils;

import freemarker.template.TemplateModelException;

public class MustNumberException extends TemplateModelException{

	public MustNumberException(String paramString)
	  {
	    super("The \"" + paramString + "\" parameter must be a number.");
	  }
}
