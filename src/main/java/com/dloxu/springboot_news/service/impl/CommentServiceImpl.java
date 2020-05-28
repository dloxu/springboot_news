package com.dloxu.springboot_news.service.impl;

import com.dloxu.springboot_news.dao.CommentDao;
import com.dloxu.springboot_news.model.Comment;
import com.dloxu.springboot_news.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Description:新闻评论service实现类
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:43
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public int add(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.add(comment);
	}

	@Override
	public int edit(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.edit(comment);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return commentDao.delete(ids);
	}

	@Override
	public List<Comment> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return commentDao.findList(queryMap);
	}

	@Override
	public List<Comment> findAll() {
		// TODO Auto-generated method stub
		return commentDao.findAll();
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return commentDao.getTotal(queryMap);
	}
	
	

}
