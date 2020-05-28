package com.dloxu.springboot_news.dao;


import com.dloxu.springboot_news.model.News;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description:ÐÂÎÅdao
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:36
 */
@Repository
public interface NewsDao {
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
