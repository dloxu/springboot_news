package com.dloxu.springboot_news.service;

import com.dloxu.springboot_news.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description:½ÇÉ«role service
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:26
 */
@Service
public interface RoleService {
	 int add(Role role);
	 int edit(Role role);
	 int delete(Long id);
	 List<Role> findList(Map<String, Object> queryMap);
	 int getTotal(Map<String, Object> queryMap);
	 Role find(Long id);
}
