/**
 * 
 */
package com.bplow.netconn.aom.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.bplow.netconn.aom.service.Command;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * @desc 执行shell 脚本
 * @author wangxiaolei
 * @date 2016年4月17日 上午10:06:54
 */
@Service
public class ExecuteShellCommand implements Command,InitializingBean{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	String charset = "UTF-8";
	private String host ="";
	private String port ="";
	private String username ="";
	private String password = "";
	
	private ChannelExec sshChannel = null;
	
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	private String command = "";
	
	
	public void init() throws JSchException{
		this.setHost("115.28.182.11");
		this.setPassword("ang6789355");
		this.setPort("22");
		this.setUsername("root");
		JSch jsch=new JSch();
		Session session=jsch.getSession(username, host, 22);
		
		Properties sshConfig = new Properties();
		sshConfig.put("StrictHostKeyChecking", "no");
		session.setConfig(sshConfig);
		session.setPassword(password);
		session.connect(30000);
		sshChannel=(ChannelExec)session.openChannel("exec");
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.aom.service.Command#execute()
	 */
	@Override
	public boolean execute() throws Exception {
		boolean result = false;
		
		sshChannel.setInputStream(null);  
		sshChannel.setErrStream(System.err);  
          
		sshChannel.setCommand(command);
		sshChannel.connect();
		
		
		
        InputStream in = sshChannel.getInputStream();  
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName(charset)));  
        String buf = null;
        while ((buf = reader.readLine()) != null)  
        {  
            System.out.println(buf);  
        }  
        reader.close();
        sshChannel.disconnect();  
        sshChannel.disconnect();
		
		
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
