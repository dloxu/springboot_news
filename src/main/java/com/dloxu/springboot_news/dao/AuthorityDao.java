package com.dloxu.springboot_news.dao;

;
import com.dloxu.springboot_news.model.Authority;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:权限实现类dao
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:34
 */
@Repository
public interface AuthorityDao {
	 int add(Authority authority);
	 int deleteByRoleId(Long roleId);
	 List<Authority> findListByRoleId(Long roleId);
}
