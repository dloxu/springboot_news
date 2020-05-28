package com.dloxu.springboot_news.controller.admin;


import com.dloxu.springboot_news.model.Comment;
import com.dloxu.springboot_news.model.Page;
import com.dloxu.springboot_news.service.CommentService;
import com.dloxu.springboot_news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:新闻评论控制器
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:08
 */
@RequestMapping("/admin/comment")
@Controller
public class CommentController {
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * Description:新闻评论列表页面
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:09
	 */
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 999);
		model.addObject("newsList", newsService.findList(queryMap));
		model.setViewName("comment/list");
		return model;
	}
	
	/**
	 * Description:新闻评论添加
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:09
	 */
	@RequestMapping(value="/add",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,String> add(Comment comment){
		Map<String,String> ret = new HashMap<String, String>();
		if(comment == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的评论信息！");
			return ret;
		}
		if(comment.getNewsId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择要评论的新闻！");
			return ret;
		}
		if(StringUtils.isEmpty(comment.getNickname())){
			ret.put("type", "error");
			ret.put("msg", "评论昵称不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(comment.getContent())){
			ret.put("type", "error");
			ret.put("msg", "评论内容不能为空！");
			return ret;
		}
		comment.setCreateTime(new Date());
		if(commentService.add(comment) <= 0){
			ret.put("type", "error");
			ret.put("msg", "评论添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		//新闻评论数量加1
		newsService.updateCommentNumber(comment.getNewsId());
		return ret;
	}
	
	/**
	 * Description:新闻评论信息编辑
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:09
	 */
	@RequestMapping(value="/edit",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,String> edit(Comment comment){
		Map<String,String> ret = new HashMap<String, String>();
		if(comment == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的评论信息！");
			return ret;
		}
		if(comment.getNewsId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择要评论的新闻！");
			return ret;
		}
		if(StringUtils.isEmpty(comment.getNickname())){
			ret.put("type", "error");
			ret.put("msg", "评论昵称不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(comment.getContent())){
			ret.put("type", "error");
			ret.put("msg", "评论内容不能为空！");
			return ret;
		}
		if(commentService.edit(comment) <= 0){
			ret.put("type", "error");
			ret.put("msg", "评论修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功！");
		return ret;
	}
	
	/**
	 * Description:删除新闻评论
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:09
	 */
	@RequestMapping(value="/delete",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(String ids){
		Map<String,String> ret = new HashMap<String, String>();
		if(ids == null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的评论信息！");
			return ret;
		}
		if(ids.contains(",")){
			ids = ids.substring(0,ids.length()-1);
		}
		if(commentService.delete(ids) <= 0){
			ret.put("type", "error");
			ret.put("msg", "评论删除失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
	}
	
	/**
	 * Description:分页模糊搜索查询列表
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:09
	 */
	@RequestMapping(value="/list",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getList(
			@RequestParam(name="nickname",required=false,defaultValue="") String nickname,
			@RequestParam(name="content",required=false,defaultValue="") String content,
			Page page
			){
		Map<String,Object> ret = new HashMap<String, Object>();
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("nickname", nickname);
		queryMap.put("content", content);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", commentService.findList(queryMap));
		ret.put("total", commentService.getTotal(queryMap));
		return ret;
	}
}
