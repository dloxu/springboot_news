package com.dloxu.springboot_news.service.impl;

import com.dloxu.springboot_news.dao.RoleDao;
import com.dloxu.springboot_news.model.Role;
import com.dloxu.springboot_news.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Description:角色role的实现类
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:47
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public int add(Role role) {
		// TODO Auto-generated method stub
		return roleDao.add(role);
	}

	@Override
	public int edit(Role role) {
		// TODO Auto-generated method stub
		return roleDao.edit(role);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return roleDao.delete(id);
	}

	@Override
	public List<Role> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return roleDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return roleDao.getTotal(queryMap);
	}

	@Override
	public Role find(Long id) {
		// TODO Auto-generated method stub
		return roleDao.find(id);
	}

}
