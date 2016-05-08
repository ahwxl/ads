/**
 * www.bplow.com
 */
package com.bplow.netconn.systemmng.domain;

/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年5月8日 下午5:01:50
 */
public class OrganizetionDomain {

	private String orgId;
	private String orgName;
	private String orgDesc;
	private String parentOrgId;
	
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
	
}
