package com.dloxu.springboot_news.service;


import com.dloxu.springboot_news.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description:user用户service
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:30
 */
@Service
public interface UserService {
	 User findByUsername(String username);
	 int add(User user);
	 int registerUser(User user);
	 int edit(User user);
	 int editPassword(User user);
	 int delete(String ids);
	 List<User> findList(Map<String, Object> queryMap);
	 int getTotal(Map<String, Object> queryMap);
}
