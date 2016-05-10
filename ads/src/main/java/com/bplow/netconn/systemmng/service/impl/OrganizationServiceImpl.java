/**
 * www.bplow.com
 */
package com.bplow.netconn.systemmng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bplow.netconn.base.dao.pagination.IPagination;
import com.bplow.netconn.systemmng.dao.SysOrganizationDAO;
import com.bplow.netconn.systemmng.dao.entity.SysOrganization;
import com.bplow.netconn.systemmng.domain.OrganizetionDomain;
import com.bplow.netconn.systemmng.service.OrganizationService;

/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年5月10日 下午10:25:02
 */
@Service("OrganizationServiceImpl")
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private SysOrganizationDAO sysOrganizationDAO;
	
	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.OrganizationService#queryOrganizetionList(com.bplow.netconn.systemmng.domain.OrganizetionDomain)
	 */
	@Override
	public List<OrganizetionDomain> queryOrganizetionList(OrganizetionDomain org) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.OrganizationService#queryOrganizetionById(java.lang.String)
	 */
	@Override
	public OrganizetionDomain queryOrganizetionById(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.OrganizationService#addOrganizetion(com.bplow.netconn.systemmng.domain.OrganizetionDomain)
	 */
	@Override
	public void addOrganizetion(OrganizetionDomain org) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.OrganizationService#deleteOrganizetion(com.bplow.netconn.systemmng.domain.OrganizetionDomain)
	 */
	@Override
	public void deleteOrganizetion(OrganizetionDomain org) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.OrganizationService#updateOrganizetion(com.bplow.netconn.systemmng.domain.OrganizetionDomain)
	 */
	@Override
	public void updateOrganizetion(OrganizetionDomain org) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.OrganizationService#queryOrganizetionListForExtGrid(com.bplow.netconn.systemmng.domain.OrganizetionDomain)
	 */
	@Override
	public String queryOrganizetionListForExtGrid(OrganizetionDomain org) {
		SysOrganization record = new SysOrganization();
		record.setOrganizeName(org.getOrgName());
		record.setOrganizeDesc(org.getOrgDesc());
		record.setPageNum(org.getPageNum());
		record.setMaxRowNums(org.getMaxRowNums());
		String str = null;
		try {
			IPagination page = sysOrganizationDAO.queryOrgPage(record);
            str = page.getJsonByList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

}
