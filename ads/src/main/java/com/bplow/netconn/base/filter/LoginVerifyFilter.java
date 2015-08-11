package com.bplow.netconn.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class LoginVerifyFilter
    implements Filter
{

    public LoginVerifyFilter()
    {
    }

    public void init(FilterConfig filterConfig)
        throws ServletException
    {
        String value = filterConfig.getInitParameter("pass");
        if(value != null)
        	pass = value.split(";");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        HttpServletRequest r = (HttpServletRequest)request;
        HttpServletResponse p = (HttpServletResponse)response;
        if(needSkipUserLoginCheck(r))
        {
            chain.doFilter(request, response);
            return;
        }
        if(r.getSession() != null && r.getSession().getAttribute("lgu") != null)
        {
            chain.doFilter(request, response);
        } else
        {
        	p.setHeader("Content-Type", "text/html; charset=UTF-8");
            String script = (new StringBuilder("<script language='javascript'>alert('\u5C0A\u656C\u7684\u7528\u6237,\u7531\u4E8E\u957F\u65F6\u95F4\u6CA1\u6709\u64CD\u4F5C,\u5BFC\u81F4\u7CFB\u7EDF\u8D85\u65F6\u8BF7\u91CD\u65B0\u767B\u9646\u7CFB\u7EDF!');top.location.href='")).append(r.getContextPath()).append("/login/loginPage.action'</script>").toString();
            //script = new String(script.getBytes("GBK"), "ISO8859-1");
            script = new String (script.getBytes("UTF-8"), "ISO8859-1");
            p.getOutputStream().print(script);
        }
    }

    private boolean needSkipUserLoginCheck(HttpServletRequest r)
    {
        boolean result = false;
        String uri = r.getRequestURI();
        if(uri.equals(r.getContextPath()) || uri.equals((new StringBuilder(String.valueOf(r.getContextPath()))).append("/").toString()))
            return true;
        String as[];
        int j = (as = pass).length;
        for(int i = 0; i < j; i++)
        {
            String s = as[i];
            if(uri.indexOf(s) <= -1)
                continue;
            result = true;
            break;
        }

        return result;
    }

    public void destroy()
    {
    }

    private static final Log log = LogFactory.getLog(LoginVerifyFilter.class);
    private static final String USER = "lgu";
    private static String pass[] = null;

}
