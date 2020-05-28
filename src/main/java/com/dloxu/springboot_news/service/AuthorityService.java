package com.dloxu.springboot_news.service;

import com.dloxu.springboot_news.model.Authority;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:权限service接口
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:26
 */
@Service
public interface AuthorityService {
	  int add(Authority authority);
	  int deleteByRoleId(Long roleId);
	  List<Authority> findListByRoleId(Long roleId);
}
