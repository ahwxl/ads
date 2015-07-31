package com.bplow.netconn.newcms.directive;


import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Service("productListDirective")
public class productListDirective implements TemplateDirectiveModel{

	@Override
	public void execute(Environment env, Map map, TemplateModel[] tm,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		
		env.setVariable("wxl", ObjectWrapper.DEFAULT_WRAPPER.wrap("网校类"));
		body.render(env.getOut());
	}

}
 