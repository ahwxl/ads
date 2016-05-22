/**
 * www.bplow.com
 */
package com.bplow.netconn.systemmng.domain;

import com.bplow.netconn.base.dao.domain.PageImpl;

/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年5月8日 下午5:01:50
 */
public class OrganizetionDomain extends PageImpl{

	private String orgId;
	private String orgName;
	private String orgDesc;
	private String parentOrgId;
	private String userId;
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgDesc() {
		return orgDesc;
	}
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
	public String getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
