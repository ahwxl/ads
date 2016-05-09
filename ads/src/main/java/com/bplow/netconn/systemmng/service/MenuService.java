/**
 * www.bplow.com
 */
package com.bplow.netconn.systemmng.service;

import java.sql.SQLException;
import java.util.List;

import com.bplow.netconn.systemmng.domain.MenuDomain;

/**
 * @desc
 * @author wangxiaolei
 * @date 2016年5月8日 下午4:23:06
 */
public interface MenuService {

	public List<MenuDomain> queryMenuList(MenuDomain menu) throws SQLException;

	public MenuDomain queryMenuById(String menuId) throws SQLException;

	public void addMenu(MenuDomain menu) throws SQLException;

	public void deleteMenu() throws SQLException;

	public void updateMenu() throws SQLException;

	public String obtainUserMenuForExtTree(MenuDomain menu) throws SQLException;

}
