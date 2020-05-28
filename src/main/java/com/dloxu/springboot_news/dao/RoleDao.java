package com.dloxu.springboot_news.dao;


import com.dloxu.springboot_news.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description:½ÇÉ«role dao
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:36
 */
@Repository
public interface RoleDao {
	 int add(Role role);
	 int edit(Role role);
	 int delete(Long id);
	 List<Role> findList(Map<String, Object> queryMap);
	 int getTotal(Map<String, Object> queryMap);
	 Role find(Long id);
}
