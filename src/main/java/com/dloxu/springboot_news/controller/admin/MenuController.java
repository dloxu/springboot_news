package com.dloxu.springboot_news.controller.admin;


import com.dloxu.springboot_news.model.Menu;
import com.dloxu.springboot_news.model.Page;
import com.dloxu.springboot_news.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:菜单管理控制器
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:18
 */
@RequestMapping("/admin/menu")
@Controller
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	
	/**
	 * Description:菜单管理列表页
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:18
	 */
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.addObject("topList", menuService.findTopList());
		model.setViewName("menu/list");
		return model;
	}
	
	/**
	 * Description:获取菜单列表
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:18
	 */
	@RequestMapping(value="/list",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMenuList(Page page,
										   @RequestParam(name="name",required=false,defaultValue="") String name
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("name", name);
		List<Menu> findList = menuService.findList(queryMap);
		ret.put("rows", findList);
		ret.put("total", menuService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * Description:获取指定目录下的系统icon集合
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:18
	 */
	@RequestMapping(value="/get_icons",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getIconList(HttpServletRequest request){
		Map<String, Object> ret = new HashMap<String, Object>();
		String realPath = request.getServletContext().getRealPath("/");
		File file = new File(realPath + "\\static\\admin\\easyui\\css\\icons");
		List<String> icons = new ArrayList<String>();
		if(!file.exists()){
			ret.put("type", "error");
			ret.put("msg", "文件目录不存在！");
			return ret;
		}
		File[] listFiles = file.listFiles();
		for(File f:listFiles){
			if(f!= null && f.getName().contains("png")){
				icons.add("icon-" + f.getName().substring(0, f.getName().indexOf(".")).replace("_", "-"));
			}
		}
		ret.put("type", "success");
		ret.put("content", icons);
		return ret;
	}
	/**
	 * Description:菜单添加
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:19
	 */
	@RequestMapping(value="/add",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Menu menu){
		Map<String, String> ret = new HashMap<String, String>();
		if(menu == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的菜单信息!");
			return ret;
		}
		if(StringUtils.isEmpty(menu.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写菜单名称!");
			return ret;
		}
		if(StringUtils.isEmpty(menu.getIcon())){
			ret.put("type", "error");
			ret.put("msg", "请填写菜单图标类!");
			return ret;
		}
		if(menu.getParentId() == null){
			menu.setParentId(0l);
		}
		if(menuService.add(menu) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功!");
		return ret;
	}
	
	/**
	 * Description:菜单修改
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:19
	 */
	@RequestMapping(value="/edit",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Menu menu){
		Map<String, String> ret = new HashMap<String, String>();
		if(menu == null){
			ret.put("type", "error");
			ret.put("msg", "请选择正确的菜单信息!");
			return ret;
		}
		if(StringUtils.isEmpty(menu.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写菜单名称!");
			return ret;
		}
		if(StringUtils.isEmpty(menu.getIcon())){
			ret.put("type", "error");
			ret.put("msg", "请填写菜单图标类!");
			return ret;
		}
		if(menu.getParentId() == null){
			menu.setParentId(0l);
		}
		if(menuService.edit(menu) <= 0){
			ret.put("type", "error");
			ret.put("msg", "修改失败，请联系管理员!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功!");
		return ret;
	}
	
	/**
	 * Description:删除菜单信息
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:19
	 */
	@RequestMapping(value="/delete",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(
			@RequestParam(name="id",required=true) Long id
			){
		Map<String, String> ret = new HashMap<String, String>();
		if(id == null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的菜单信息!");
			return ret;
		}
		List<Menu> findChildernList = menuService.findChildernList(id);
		if(findChildernList != null && findChildernList.size() > 0){
			//表示该分类下存在子分类，不能删除
			ret.put("type", "error");
			ret.put("msg", "该分类下存在子分类，不能删除!");
			return ret;
		}
		if(menuService.delete(id) <= 0){
			ret.put("type", "error");
			ret.put("msg", "删除失败，请联系管理员!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功!");
		return ret;
	}
}
