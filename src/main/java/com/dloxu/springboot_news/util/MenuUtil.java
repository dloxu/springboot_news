package com.dloxu.springboot_news.util;


import com.dloxu.springboot_news.model.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ڲ˵�������һЩ���÷���
 * @author llq
 *
 */
public class MenuUtil {

	/**
	 * Description:�Ӹ����Ĳ˵��з������ж����˵�
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
	 * Description:��ȡ���еĶ����˵�
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
	 * Description:��ȡĳ�������˵��µİ�ť
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
