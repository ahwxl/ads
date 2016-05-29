/**
 * www.bplow.com
 */
package com.bplow.netconn.base.utils;

/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年5月29日 下午5:09:01
 */
public class Result<T> {
	
	private boolean success = false;
	
	private String info;
	
	private T obj;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	@Override
	public String toString() {
		return String.format("{'success':%s, 'info':'%s'}", success,
				info);
	}
	
	

}
