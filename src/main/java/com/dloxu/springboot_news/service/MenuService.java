package com.dloxu.springboot_news.service;

import com.dloxu.springboot_news.model.Menu;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description:菜单管理service
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:19
 */
@Service
public interface MenuService {
	 int add(Menu menu);
	 List<Menu> findList(Map<String, Object> queryMap);
	 List<Menu> findTopList();
	 int getTotal(Map<String, Object> queryMap);
	 int edit(Menu menu);
	 int delete(Long id);
	 List<Menu> findChildernList(Long parentId);
	 List<Menu> findListByIds(String ids);
}
