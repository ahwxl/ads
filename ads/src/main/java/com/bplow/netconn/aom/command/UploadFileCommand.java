package com.bplow.netconn.aom.command;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.ProgressMonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.bplow.netconn.aom.service.Command;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

/**
 * 上传文件到sftp
 * @desc 
 * @author wangxiaolei
 * @date 2016年4月17日 上午10:06:15
 */
@Service
public class UploadFileCommand implements Command,InitializingBean{
    
    private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ChannelSftp channelSftp;
	
	private String      descFileName;
	
	private String host;
	
	private String password;
	
	private String userName;
	
	private String desPath;
	
	private int port;
	
	private InputStream srcFileInput = null;
	
	public void init() throws JSchException{
	    
		JSch jsch=new JSch();
		Session sshSession = jsch.getSession(this.userName, this.host, this.port);
		sshSession.setPassword(this.password);
		Properties sshConfig = new Properties();
		sshConfig.put("StrictHostKeyChecking", "no");
		sshSession.setConfig(sshConfig);
		sshSession.connect();
		// username and password will be given via UserInfo interface.
		//session.setUserInfo(ui);
		//session.setPassword(password);

		Channel channel = sshSession.openChannel("sftp");
		channel.connect();
		channelSftp = (ChannelSftp) channel;

		/*java.io.InputStream in = System.in;
		java.io.PrintStream out = System.out;*/
		
	}

	@Override
	public boolean execute() {
	    logger.info("上传文件{}到{}",descFileName,desPath);
	    
		boolean result = false;
	    //SftpProgressMonitor monitor=new MyProgressMonitor();
		//int mode=ChannelSftp.OVERWRITE;
		try {
			channelSftp.cd(desPath);
			channelSftp.put(srcFileInput, this.descFileName);
			result = true;
		} catch (SftpException e) {
			e.printStackTrace();
		}
		finally{
			try {
				srcFileInput.close();
				//channelSftp.getSession().disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static class MyProgressMonitor implements SftpProgressMonitor{
	    ProgressMonitor monitor;
	    long count=0;
	    long max=0;
	    public void init(int op, String src, String dest, long max){
	      this.max=max;
	      monitor=new ProgressMonitor(null, 
	                                  ((op==SftpProgressMonitor.PUT)? 
	                                   "put" : "get")+": "+src, 
	                                  "",  0, (int)max);
	      count=0;
	      percent=-1;
	      monitor.setProgress((int)this.count);
	      monitor.setMillisToDecideToPopup(1000);
	    }
	    private long percent=-1;
	    public boolean count(long count){
	      this.count+=count;

	      if(percent>=this.count*100/max){ return true; }
	      percent=this.count*100/max;

	      monitor.setNote("Completed "+this.count+"("+percent+"%) out of "+max+".");     
	      monitor.setProgress((int)this.count);

	      return !(monitor.isCanceled());
	    }
	    public void end(){
	      monitor.close();
	    }
	  }

	public String getDesPath() {
		return desPath;
	}

	public void setDesPath(String desPath) {
		this.desPath = desPath;
	}

	public InputStream getSrcFileInput() {
		return srcFileInput;
	}

	public void setSrcFileInput(InputStream srcFileInput) {
		this.srcFileInput = srcFileInput;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
		
	}

    public String getDescFileName() {
        return descFileName;
    }

    public void setDescFileName(String descFileName) {
        this.descFileName = descFileName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
