package com.bplow.netconn.query.service;

public interface LogConfigService {
	
	/**
	 * 设置最大日志记录条数
	 * @param errNum
	 */
	void setMaxErrorLogNum(int errNum);
	/**
	 * 获取最大日志条数
	 * @return
	 */
	int getMaxErrorLogNum();
	
	/**
	 * 是否可以写入日志
	 * @return
	 */
	boolean canWriteLog();
	
	/**
	 * 添加黑名单
	 */
	void addBlackName(String blackIpAddr);
	
	/**
	 * 是否是黑名单
	 * @param ipAddr
	 * @return
	 */
	boolean isBlackName(String ipAddr);
	
	/**
	 * 切换日志写入开关
	 */
	void switchOpenLog();
	
	/**
	 * 是否记录日志
	 * @return
	 */
	boolean enableLog();
	/**
	 * 切换日志发送开关
	 */
	void switchSendMsgLog();
	
	boolean canSendMsgLog();

}
