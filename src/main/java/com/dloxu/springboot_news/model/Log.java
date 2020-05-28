package com.dloxu.springboot_news.model;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description:系统日志
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:41
 */
@Component
public class Log {
	private Long id;
	
	private String content;//日志内容
	
	private Date createTime;//创建时间

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
