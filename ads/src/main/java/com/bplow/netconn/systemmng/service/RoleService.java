/**
 * www.bplow.com
 */
package com.bplow.netconn.systemmng.service;

import java.util.List;

import com.bplow.netconn.base.dao.pagination.IPagination;
import com.bplow.netconn.systemmng.dao.entity.SysRole;
import com.bplow.netconn.systemmng.domain.MenuDomain;
import com.bplow.netconn.systemmng.domain.RoleDomain;
import com.bplow.netconn.systemmng.domain.UserDomain;

/**
 * @desc 角色
 * @author wangxiaolei
 * @date 2016年5月8日 下午4:33:30
 */
public interface RoleService {
	
	public List<RoleDomain> queryRoleList(RoleDomain role);
	
	public RoleDomain queryRole(String roleId);
	
	public IPagination queryRolePage(SysRole record)throws Exception;
	
	public void addRole(RoleDomain role)throws Exception;
	
	public void updateRole(RoleDomain role)throws Exception;
	
	public void deleteRole(RoleDomain role)throws Exception;
	/**
	 * 查询用户角色
	 * @param user
	 * @return
	 */
	public List<RoleDomain> queryUserRolesByUserId(UserDomain user);
	
	/**
	 * 查询菜单角色
	 * @param menu
	 * @return
	 */
	public List<RoleDomain> queryMenuRolesByMenuId(MenuDomain menu);
	
	/**
	 * 查询角色列表
	 * @param role
	 * @return
	 */
	public String queryRoleListForExtGrid(RoleDomain role);
	
	/**
	 * 用户授权
	 * @param user
	 */
	public void authorizeUserRoles(UserDomain user);
	
	/**
	 * 菜单授权
	 * @param menu
	 */
	public void authorizeMenuRoles(MenuDomain menu);

}
