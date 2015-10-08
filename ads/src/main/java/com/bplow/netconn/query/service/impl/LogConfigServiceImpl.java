package com.bplow.netconn.query.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.test.context.BootstrapWith;

import com.bplow.netconn.query.service.LogConfigService;

@Service("logConfigService")
public class LogConfigServiceImpl implements LogConfigService{

	private int maxErrorLogNum = 5000;
	
	private int currentLogNum = 0;
	
	private boolean enableLog = false;
	
	private boolean sendEnable = false;
	

	public void setSendEnable(Boolean sendEnable) {
		this.sendEnable = sendEnable;
	}

	private Set<String> blackNameSet = new HashSet<String>();
	
	@Override
	public void setMaxErrorLogNum(int errNum) {
		this.maxErrorLogNum = errNum;
	}

	@Override
	public int getMaxErrorLogNum() {
        return this.maxErrorLogNum;
	}

	@Override
	public boolean canWriteLog() {
		if(currentLogNum < maxErrorLogNum){
			currentLogNum ++;
			return true;
		}
		return false;
	}

	@Override
	public void addBlackName(String blackIpAddr) {
		blackNameSet.add(blackIpAddr);
	}

	@Override
	public boolean isBlackName(String ipAddr) {
		if(!blackNameSet.contains(ipAddr)){
			return true;
		}
		return false;
	}

	@Override
	public void switchOpenLog() {
		if(enableLog){
			enableLog = false;
		}else{
			enableLog = true;
		}
	}

	@Override
	public boolean enableLog() {
		return enableLog;
	}

	@Override
	public void switchSendMsgLog() {
		if(sendEnable){
			sendEnable = false;
		}else{
			sendEnable = true;
		}
		
	}

	@Override
	public boolean canSendMsgLog() {
		return sendEnable;
	}
	

}
