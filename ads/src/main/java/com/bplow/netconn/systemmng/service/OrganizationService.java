/**
 * www.bplow.com
 */
package com.bplow.netconn.systemmng.service;

import java.util.List;

import com.bplow.netconn.systemmng.domain.OrganizetionDomain;

/**
 * @desc 机构管理
 * @author wangxiaolei
 * @date 2016年5月8日 下午5:00:15
 */
public interface OrganizationService {

	public List<OrganizetionDomain> queryOrganizetionList(OrganizetionDomain org);
	
	public OrganizetionDomain queryOrganizetionById(String orgId);
	
	public void addOrganizetion(OrganizetionDomain org);
	
	public void deleteOrganizetion(OrganizetionDomain org);
	
	public void updateOrganizetion(OrganizetionDomain org);
	
	public String queryOrganizetionListForExtGrid(OrganizetionDomain org);
	
	
	
	
}
