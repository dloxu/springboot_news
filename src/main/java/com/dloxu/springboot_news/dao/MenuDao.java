package com.dloxu.springboot_news.dao;

import com.dloxu.springboot_news.model.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description:菜单管理dao
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:35
 */
@Repository
public interface MenuDao {
	 int add(Menu menu);
	 List<Menu> findList(Map<String, Object> queryMap);
	 List<Menu> findTopList();
	 int getTotal(Map<String, Object> queryMap);
	 int edit(Menu menu);
	 int delete(Long id);
	 List<Menu> findChildernList(Long parentId);
	 List<Menu> findListByIds(String ids);
}
