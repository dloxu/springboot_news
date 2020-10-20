package com.dloxu.springboot_news.dao;

import com.dloxu.springboot_news.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description:新闻评论dao
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:34
 */
@Repository
public interface CommentDao {
	 int add(Comment comment);
	 int edit(Comment comment);
	 int delete(String ids);
	 List<Comment> findList(Map<String, Object> queryMap);
	 List<Comment> findAll();
	 int getTotal(Map<String, Object> queryMap);
}
