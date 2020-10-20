package com.dloxu.springboot_news.model;

import org.springframework.stereotype.Component;

/**
 * Description:权限实体
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:40
 */

@Component
public class Authority {
	private Long id;
	
	private Long roleId;//角色id
	
	private Long menuId;//菜单id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
	
}
