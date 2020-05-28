package com.dloxu.springboot_news.dao;

import com.dloxu.springboot_news.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description:user”√ªßdao
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:36
 */
@Repository
public interface UserDao {
	 User findByUsername(String username);
	 int registerUser(@Param("user") User user);
	 int add(User user);
	 int edit(User user);
	 int editPassword(User user);
	 int delete(String ids);
	 List<User> findList(Map<String, Object> queryMap);
	 int getTotal(Map<String, Object> queryMap);
}
