package com.dloxu.springboot_news.model;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description:ϵͳ��־
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:41
 */
@Component
public class Log {
	private Long id;
	
	private String content;//��־����
	
	private Date createTime;//����ʱ��

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	
}
