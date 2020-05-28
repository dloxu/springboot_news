package com.dloxu.springboot_news.service;



import com.dloxu.springboot_news.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description:新闻评论接口
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:20
 */
@Service
public interface CommentService {
	 int add(Comment comment);
	 int edit(Comment comment);
	 int delete(String ids);
	 List<Comment> findList(Map<String, Object> queryMap);
	 List<Comment> findAll();
	 int getTotal(Map<String, Object> queryMap);
}
