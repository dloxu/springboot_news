package com.dloxu.springboot_news.model;

import org.springframework.stereotype.Component;


/**
 * Description:角色role实体
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:42
 */
@Component
public class Role {
	
	private Long id;
	
	private String name;
	
	private String remark;//角色备注

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
