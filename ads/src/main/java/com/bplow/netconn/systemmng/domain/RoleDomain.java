/**
 * www.bplow.com
 */
package com.bplow.netconn.systemmng.domain;

/**
 * @desc 角色
 * @author wangxiaolei
 * @date 2016年5月8日 下午4:30:39
 */
public class RoleDomain {
	
	private String roleId;
	private String userId;
	private String roleName;
	private String roleDesc;
	
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
}
