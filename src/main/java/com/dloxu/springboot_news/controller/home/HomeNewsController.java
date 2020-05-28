package com.dloxu.springboot_news.controller.home;

import com.dloxu.springboot_news.model.*;
import com.dloxu.springboot_news.service.CommentService;
import com.dloxu.springboot_news.service.NewsCategoryService;
import com.dloxu.springboot_news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:ǰ̨���ſ�����
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:31
 */
@RequestMapping("/news")
@Controller
public class HomeNewsController {
	
	@Autowired
	private NewsCategoryService newsCategoryService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private CommentService commentService;
	
	
	/**
	 * Description:�鿴��������
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:32
	 */
	@RequestMapping(value="/detail",method= RequestMethod.GET)
	public ModelAndView detail(ModelAndView model, Long id){
		model.addObject("newsCategoryList", newsCategoryService.findAll());
		News news = newsService.find(id);
		model.addObject("news", news);
		model.addObject("title", news.getTitle());
		model.addObject("tags", news.getTags().split(","));
		model.setViewName("home/news/detail");
		//�鿴����1
		newsService.updateViewNumber(id);
		return model;
	}
	
	/**
	 * Description:���շ�����ʾ�����б�
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:32
	 */
	@RequestMapping(value="/category_list",method= RequestMethod.GET)
	public ModelAndView categoryList(ModelAndView model,
                                     @RequestParam(name="cid",required=true) Long cid,
                                     Page page
			){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 10);
		queryMap.put("categoryId", cid);
		model.addObject("newsCategoryList", newsCategoryService.findAll());
		model.addObject("newsList", newsService.findList(queryMap));
		NewsCategory newsCategory = newsCategoryService.find(cid);
		model.addObject("newsCategory", newsCategory);
		model.addObject("title", newsCategory.getName() + "�����µ�������Ϣ");
		model.setViewName("home/news/category_list");
		return model;
	}
	
	/**
	 * Description:��ȡ�����������������n����Ϣ
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:32
	 */
	@RequestMapping(value="/last_comment_list",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> lastCommentList(){
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("type", "success");
		ret.put("newsList", newsService.findLastCommentList(10));
		return ret;
	}
	
	
	/**
	 * Description:��ҳ��ȡĳ�������µ�����
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:32
	 */
	@RequestMapping(value="/get_category_list",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCategoryList(Page page,
			@RequestParam(name="cid",required=true) Long cid
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("categoryId", cid);
		ret.put("type", "success");
		ret.put("newsList", newsService.findList(queryMap));
		return ret;
	}
	
	/**
	 * Description:��ȡ�����б�
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:32
	 */
	@RequestMapping(value="/search_list",method= RequestMethod.GET)
	public ModelAndView searchList(ModelAndView model,
                                   @RequestParam(name="keyword",required=false,defaultValue="") String keyword,
                                   Page page
			){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 10);
		queryMap.put("title", keyword);
		model.addObject("newsCategoryList", newsCategoryService.findAll());
		model.addObject("newsList", newsService.findList(queryMap));
		model.addObject("title", keyword + "�ؼ����µ�������Ϣ");
		model.addObject("keyword", keyword);
		model.setViewName("home/news/search_list");
		return model;
	}
	
	/**
	 * Description:��ҳ���������б�
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:32
	 */
	@RequestMapping(value="/get_search_list",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSearchList(Page page,
			@RequestParam(name="keyword",required=false,defaultValue="") String keyword
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("title", keyword);
		ret.put("type", "success");
		ret.put("newsList", newsService.findList(queryMap));
		return ret;
	}
	
	/**
	 * Description:�������
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:33
	 */
	@RequestMapping(value="/comment_news",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> commentNews(Comment comment,HttpServletRequest request){
		Map<String, Object> ret = new HashMap<String, Object>();
		User loginUser= (User) request.getSession().getAttribute("admin");
		Role loginRole= (Role) request.getSession().getAttribute("role");
		if (loginRole==null||loginUser==null){
			ret.put("type", "error");
			ret.put("msg", "���ȵ�¼��");
			return ret;
		}
		if(comment == null){
			ret.put("type", "error");
			ret.put("msg", "����д������������Ϣ��");
			return ret;
		}
		if(comment.getNewsId() == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��һ�����½������ۣ�");
			return ret;
		}
//		if(StringUtils.isEmpty(comment.getNickname())){
//			ret.put("type", "error");
//			ret.put("msg", "����д�ǳƣ�");
//			return ret;
//		}
		if(StringUtils.isEmpty(comment.getContent())){
			ret.put("type", "error");
			ret.put("msg", "����д�������ݣ�");
			return ret;
		}
		comment.setCreateTime(new Date());
		comment.setNickname(loginUser.getUsername());
		if(commentService.add(comment) <= 0){
			ret.put("type", "error");
			ret.put("msg", "����ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		//������������1
		newsService.updateCommentNumber(comment.getNewsId());
		ret.put("type", "success");
		ret.put("createTime", comment.getCreateTime());
		ret.put("nickname",loginUser.getUsername());
		return ret;
	}
	
	/**
	 * Description:��ҳ��ȡĳһ���µ�����
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:33
	 */
	@RequestMapping(value="/get_comment_list",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCommentList(Page page,Long newsId){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("newsId", newsId);
		ret.put("type", "success");
		ret.put("commentList", commentService.findList(queryMap));
		return ret;
	}
}
