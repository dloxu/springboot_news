package com.dloxu.springboot_news.service.impl;


import com.dloxu.springboot_news.dao.NewsCategoryDao;
import com.dloxu.springboot_news.model.NewsCategory;
import com.dloxu.springboot_news.service.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Description:菜单管理实现类
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:44
 */
@Service
public class NewsCategoryServiceImpl implements NewsCategoryService {

	@Autowired
	private NewsCategoryDao newsCategoryDao;
	
	@Override
	public int add(NewsCategory newsCategory) {
		// TODO Auto-generated method stub
		return newsCategoryDao.add(newsCategory);
	}

	@Override
	public int edit(NewsCategory newsCategory) {
		// TODO Auto-generated method stub
		return newsCategoryDao.edit(newsCategory);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return newsCategoryDao.delete(id);
	}

	@Override
	public List<NewsCategory> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return newsCategoryDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return newsCategoryDao.getTotal(queryMap);
	}

	@Override
	public List<NewsCategory> findAll() {
		// TODO Auto-generated method stub
		return newsCategoryDao.findAll();
	}

	@Override
	public NewsCategory find(Long id) {
		// TODO Auto-generated method stub
		return newsCategoryDao.find(id);
	}

}
