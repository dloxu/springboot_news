package com.dloxu.springboot_news.service;

import com.dloxu.springboot_news.model.News;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description:新闻接口
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:22
 */
@Service
public interface NewsService {
	 int add(News news);
	 int edit(News news);
	 int delete(Long id);
	 List<News> findList(Map<String, Object> queryMap);
	 int getTotal(Map<String, Object> queryMap);
	 News find(Long id);
	 int updateCommentNumber(Long id);
	 int updateViewNumber(Long id);
	 List<News> findLastCommentList(int pageSize);
}
