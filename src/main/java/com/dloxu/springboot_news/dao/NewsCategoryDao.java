package com.dloxu.springboot_news.dao;

import com.dloxu.springboot_news.model.NewsCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description:新闻分类dao
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:35
 */
@Repository
public interface NewsCategoryDao {
	 int add(NewsCategory newsCategory);
	 int edit(NewsCategory newsCategory);
	 int delete(Long id);
	 List<NewsCategory> findList(Map<String, Object> queryMap);
	 List<NewsCategory> findAll();
	 int getTotal(Map<String, Object> queryMap);
	 NewsCategory find(Long id);
}
