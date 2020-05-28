package com.dloxu.springboot_news.util;


import com.dloxu.springboot_news.model.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 关于菜单操作的一些公用方法
 * @author llq
 *
 */
public class MenuUtil {

	/**
	 * Description:从给定的菜单中返回所有顶级菜单
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:08
	 */
	public static List<Menu> getAllTopMenu(List<Menu> menuList){
		List<Menu> ret = new ArrayList<Menu>();
		for(Menu menu:menuList){
			if(menu.getParentId() == 0){
				ret.add(menu);
			}
		}
		return ret;
	}
	
	/**
	 * Description:获取所有的二级菜单
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:08
	 */
	public static List<Menu> getAllSecondMenu(List<Menu> menuList){
		List<Menu> ret = new ArrayList<Menu>();
		List<Menu> allTopMenu = getAllTopMenu(menuList);
		for(Menu menu:menuList){
			for(Menu topMenu:allTopMenu){
				if(menu.getParentId() == topMenu.getId()){
					ret.add(menu);
					break;
				}
			}
		}
		return ret;
	}
	
	/**
	 * Description:获取某个二级菜单下的按钮
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:07
	 */
	public static List<Menu> getAllThirdMenu(List<Menu> menuList,Long secondMenuId){
		List<Menu> ret = new ArrayList<Menu>();
		for(Menu menu:menuList){
			if(menu.getParentId() == secondMenuId){
				ret.add(menu);
			}
		}
		return ret;
	}
}
