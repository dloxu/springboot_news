package com.dloxu.springboot_news.model;

import org.springframework.stereotype.Component;

/**
 * Description:��ҳ������Ϣ
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:42
 */
@Component
public class Page {
	private int page = 1;//��ǰҳ��
	
	private int rows;//ÿҳ��ʾ����
	
	private int offset;//��Ӧ���ݿ��е�ƫ����

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getOffset() {
		this.offset = (page - 1) * rows;
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
}
