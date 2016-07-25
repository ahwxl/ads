/**
 * www.bplow.com
 */
package com.bplow.netconn.systemmng.domain;

import java.util.List;

import com.bplow.netconn.base.dao.domain.PageImpl;

/**
 * @desc 功能菜单
 * @author wangxiaolei
 * @date 2016年5月8日 下午4:19:09
 */
public class MenuDomain extends PageImpl{
	
	private String userId;
	private String id;
	private String text;
	private String href;
	private boolean isClass;
	private String iconCls;
	private String cls;
	private String parentId;
	private boolean leaf;
	private List<MenuDomain>  children;
	private List<RoleDomain> roles;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public boolean isClass() {
		return isClass;
	}
	public void setClass(boolean isClass) {
		this.isClass = isClass;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public List<MenuDomain> getChildren() {
		return children;
	}
	public void setChildren(List<MenuDomain> children) {
		this.children = children;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<RoleDomain> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDomain> roles) {
		this.roles = roles;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
