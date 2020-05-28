package com.dloxu.springboot_news.controller.home;



import com.dloxu.springboot_news.model.User;
import com.dloxu.springboot_news.service.LogService;
import com.dloxu.springboot_news.service.NewsCategoryService;
import com.dloxu.springboot_news.service.NewsService;
import com.dloxu.springboot_news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:前台页面首页控制器
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:33
 */
@RequestMapping("/index")
@Controller
public class IndexController {
	
	@Autowired
	private NewsCategoryService newsCategoryService;
	
	@Autowired
	private NewsService newsService;

	@Autowired
	private UserService userService;

	@Autowired
	private LogService logService;
	
	/**
	 * Description:系统首页
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:33
	 */
	@RequestMapping(value="/index",method= RequestMethod.GET)
	public ModelAndView index(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 10);
		model.addObject("newsCategoryList", newsCategoryService.findAll());
		model.addObject("newsList", newsService.findList(queryMap));
		model.setViewName("home/index/index");
		return model;
	}
	
	/**
	 * Description:获取网站基本信息
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:33
	 */
	@RequestMapping(value="/get_site_info",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSiteInfo(){
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("type", "success");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 99999);
		retMap.put("totalArticle", newsService.getTotal(queryMap));
		retMap.put("siteDays", getDays("2018-02-22"));
		return retMap;
	}

	/**
	 * Description:获取日期
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:34
	 */
	private long getDays(String data){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		try {
			startDate = sdf.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date endDate = new Date();
		long time = (endDate.getTime() - startDate.getTime())/1000/3600/24;
		return time;
	}

	/**
	 * Description:
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/4/5 10:38
	 */
	@RequestMapping(value="/register",method= RequestMethod.GET)
	public ModelAndView register(ModelAndView model){
		model.setViewName("system/register");
		return model;
	}
	/**
	 * Description:
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/4/5 10:38
	 */
	@RequestMapping(value="/register",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, String> registerUser(User user, ModelAndView model){
		Map<String, String> ret = new HashMap<String, String>();
		String username=user.getUsername();
		User findByUsername = userService.findByUsername(username);
		if (findByUsername!=null&&username.equals(findByUsername.getUsername())){
			ret.put("msg","该用户已注册");
			ret.put("type", "error");
			logService.add("用户名为"+user.getUsername()+"的用户已经注册");
		}else {
			ret.put("msg","注册成功");
			ret.put("type", "success");
			user.setRoleId(2L);
			userService.registerUser(user);
		}
		return ret;
	}

	@RequestMapping(value="/login",method= RequestMethod.GET)
	public ModelAndView login(ModelAndView model){
		model.setViewName("system/login");
		return model;
	}


}
