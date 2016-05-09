/**
 * www.bplow.com
 */
package com.bplow.netconn.systemmng.domain;

import java.util.List;

/**
 * @desc 用户
 * @author wangxiaolei
 * @date 2016年5月8日 下午4:48:45
 */
public class UserDomain {
	
	private String userId;
	private String userName;
	private List<RoleDomain> roles;
	private String organizationId;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<RoleDomain> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDomain> roles) {
		this.roles = roles;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	
}
