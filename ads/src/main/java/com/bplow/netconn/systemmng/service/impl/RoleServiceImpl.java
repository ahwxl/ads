/**
 * www.bplow.com
 */
package com.bplow.netconn.systemmng.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.bplow.netconn.base.dao.pagination.IPagination;
import com.bplow.netconn.systemmng.dao.SysRoleDAO;
import com.bplow.netconn.systemmng.dao.entity.SysRole;
import com.bplow.netconn.systemmng.domain.MenuDomain;
import com.bplow.netconn.systemmng.domain.RoleDomain;
import com.bplow.netconn.systemmng.domain.UserDomain;
import com.bplow.netconn.systemmng.service.RoleService;

/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年6月11日 下午4:54:55
 */
@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private SysRoleDAO sysRoleDAO;
	
	@Autowired
	private TransactionTemplate transactionTemplate;

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.RoleService#queryRoleList(com.bplow.netconn.systemmng.domain.RoleDomain)
	 */
	@Override
	public List<RoleDomain> queryRoleList(RoleDomain role) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.RoleService#queryRole(java.lang.String)
	 */
	@Override
	public RoleDomain queryRole(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.RoleService#queryRolePage(com.bplow.netconn.systemmng.dao.entity.SysRole)
	 */
	@Override
	public IPagination queryRolePage(SysRole record) throws Exception {
		IPagination page = sysRoleDAO.selectPage(record);
		return page;
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.RoleService#addRole(com.bplow.netconn.systemmng.domain.RoleDomain)
	 */
	@Override
	public void addRole(RoleDomain role) throws Exception {
		final SysRole record = new SysRole();
		record.setRoleName(role.getRoleName());
		record.setRoleDesc(role.getRoleDesc());
		record.setRoleId(UUID.randomUUID().toString().replace("-", ""));
		
		try {
			this.transactionTemplate.execute(new TransactionCallback<Object>() {
				public Object doInTransaction(TransactionStatus status) {
					try {
						sysRoleDAO.insert(record);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return true;
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.RoleService#updateRole(com.bplow.netconn.systemmng.domain.RoleDomain)
	 */
	@Override
	public void updateRole(RoleDomain role) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.RoleService#deleteRole(com.bplow.netconn.systemmng.domain.RoleDomain)
	 */
	@Override
	public void deleteRole(RoleDomain role) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.RoleService#queryUserRolesByUserId(com.bplow.netconn.systemmng.domain.UserDomain)
	 */
	@Override
	public List<RoleDomain> queryUserRolesByUserId(UserDomain user) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.RoleService#queryMenuRolesByMenuId(com.bplow.netconn.systemmng.domain.MenuDomain)
	 */
	@Override
	public List<RoleDomain> queryMenuRolesByMenuId(MenuDomain menu) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.RoleService#queryRoleListForExtGrid(com.bplow.netconn.systemmng.domain.RoleDomain)
	 */
	@Override
	public String queryRoleListForExtGrid(RoleDomain role) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.RoleService#authorizeUserRoles(com.bplow.netconn.systemmng.domain.UserDomain)
	 */
	@Override
	public void authorizeUserRoles(UserDomain user) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.RoleService#authorizeMenuRoles(com.bplow.netconn.systemmng.domain.MenuDomain)
	 */
	@Override
	public void authorizeMenuRoles(MenuDomain menu) {
		// TODO Auto-generated method stub
		
	}

}
