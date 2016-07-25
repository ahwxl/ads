/**
 * www.bplow.com
 */
package com.bplow.netconn.systemmng.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bplow.netconn.base.dao.pagination.IPagination;
import com.bplow.netconn.systemmng.dao.SysMenuDAO;
import com.bplow.netconn.systemmng.dao.entity.SysMenu;
import com.bplow.netconn.systemmng.domain.MenuDomain;
import com.bplow.netconn.systemmng.service.MenuService;

/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年7月18日 下午10:07:10
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private SysMenuDAO sysMenuDAO;

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.MenuService#queryMenuList(com.bplow.netconn.systemmng.domain.MenuDomain)
	 */
	@Override
	public List<MenuDomain> queryMenuList(MenuDomain menu) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.MenuService#queryMenuPage(com.bplow.netconn.systemmng.domain.MenuDomain)
	 */
	@Override
	public IPagination queryMenuPage(MenuDomain menu) throws SQLException {
		SysMenu record = new SysMenu();
		record.setPageNum(menu.getPageNum());
		record.setMaxRowNums(menu.getMaxRowNums());
		
		return sysMenuDAO.selectPage(record);
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.MenuService#queryMenuById(java.lang.String)
	 */
	@Override
	public MenuDomain queryMenuById(String menuId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.MenuService#obtainUserMenuForExtTree(com.bplow.netconn.systemmng.domain.MenuDomain)
	 */
	@Override
	public String obtainUserMenuForExtTree(MenuDomain menu) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.MenuService#addMenu(com.bplow.netconn.systemmng.domain.MenuDomain)
	 */
	@Override
	public void addMenu(MenuDomain menu) throws SQLException {
		SysMenu record = new SysMenu();
		record.setId(UUID.randomUUID().toString().replace("-", ""));
		record.setMenuName(menu.getText());
		record.setUri(menu.getHref());
		sysMenuDAO.insert(record);
		
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.MenuService#deleteMenu()
	 */
	@Override
	public void deleteMenu(MenuDomain menu) throws SQLException {
		SysMenu sysMenu = new SysMenu();
		sysMenu.setId(menu.getId());
		sysMenuDAO.deleteByPrimaryKey(sysMenu);
		
	}

	/* (non-Javadoc)
	 * @see com.bplow.netconn.systemmng.service.MenuService#updateMenu()
	 */
	@Override
	public void updateMenu(MenuDomain menu) throws SQLException {
		SysMenu sysMenu = new SysMenu();
		sysMenu.setId(menu.getId());
		sysMenu.setMenuName(menu.getText());
		sysMenu.setUri(menu.getHref());
		sysMenu.setMenuDesc(menu.getText());
		sysMenu.setModuleId(menu.getParentId());
		
		sysMenuDAO.updateByPrimaryKey(sysMenu);
	}

}
