/**
 * www.bplow.com
 */
package com.bplow.netconn.systemmng.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.bplow.netconn.base.dao.pagination.IPagination;
import com.bplow.netconn.systemmng.dao.SysOrganizationDAO;
import com.bplow.netconn.systemmng.dao.SysOrganizeUserRelDAO;
import com.bplow.netconn.systemmng.dao.entity.SysOrganization;
import com.bplow.netconn.systemmng.dao.entity.SysOrganizeUserRel;
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
	
	@Autowired
	private SysOrganizeUserRelDAO sysOrganizeUserRelDAO;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
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
		final SysOrganization record = new SysOrganization();
		record.setOrganizeName(org.getOrgName());
		record.setOrganizeDesc(org.getOrgDesc());
		record.setStatus("0");
		record.setGmtCreate(new Date());
		record.setGmtModify(new Date());
		try {
			this.transactionTemplate.execute(new TransactionCallback<Object>() {
				public Object doInTransaction(TransactionStatus status) {
					try {
						sysOrganizationDAO.insert(record);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return true;
				}
			});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.OrganizationService#deleteOrganizetion(com.bplow.netconn.systemmng.domain.OrganizetionDomain)
	 */
	@Override
	public void deleteOrganizetion(final OrganizetionDomain org) throws Exception {
		try {
			this.transactionTemplate.execute(new TransactionCallback<Object>() {
				public Object doInTransaction(TransactionStatus status) {
					try {
						sysOrganizationDAO.delete(org);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return true;
				}
			});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.OrganizationService#authorizeOrgUser(com.bplow.netconn.systemmng.domain.OrganizetionDomain)
	 */
	@Override
	public String authorizeOrgUser(final OrganizetionDomain orgDomain) {
		
		this.transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus status) {
				try {
					sysOrganizeUserRelDAO.deleteByOrgId(orgDomain.getOrgId());
					
					String userId = orgDomain.getUserId();
					String[] userArray = userId.split(",");
					for(String userIdtmp : userArray){
						SysOrganizeUserRel record = new SysOrganizeUserRel();
						record.setOrganizeId(orgDomain.getOrgId());
						record.setUserId(userIdtmp);
						sysOrganizeUserRelDAO.insert(record);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return true;
			}
		});
		
		return "";
	}

}
