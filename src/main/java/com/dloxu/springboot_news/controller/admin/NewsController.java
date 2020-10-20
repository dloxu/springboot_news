package com.dloxu.springboot_news.controller.admin;


import com.dloxu.springboot_news.model.News;
import com.dloxu.springboot_news.model.Page;
import com.dloxu.springboot_news.service.NewsCategoryService;
import com.dloxu.springboot_news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:新闻控制器
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:21
 */
@RequestMapping("/admin/news")
@Controller
public class NewsController {
	
	@Autowired
	private NewsCategoryService newsCategoryService;
	
	@Autowired
	private NewsService newsService;
	
	/**
	 * Description:新闻列表页面
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:22
	 */
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.addObject("newsCategoryList", newsCategoryService.findAll());
		model.setViewName("news/list");
		return model;
	}
	
	/**
	 * Description:新闻添加页面
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:22
	 */
	@RequestMapping(value="/add",method= RequestMethod.GET)
	public ModelAndView add(ModelAndView model){
		model.addObject("newsCategoryList", newsCategoryService.findAll());
		model.setViewName("news/add");
		return model;
	}
	
	/**
	 * Description:新闻添加
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:22
	 */
	@RequestMapping(value="/add",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,String> add(News news){
		Map<String,String> ret = new HashMap<String, String>();
		if(news == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的信息！");
			return ret;
		}
		if(StringUtils.isEmpty(news.getTitle())){
			ret.put("type", "error");
			ret.put("msg", "新闻标题不能为空！");
			return ret;
		}
		if(news.getCategoryId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择新闻分类！");
			return ret;
		}
		if(StringUtils.isEmpty(news.getAbstrs())){
			ret.put("type", "error");
			ret.put("msg", "新闻摘要不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(news.getTags())){
			ret.put("type", "error");
			ret.put("msg", "新闻标签不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(news.getPhoto())){
			ret.put("type", "error");
			ret.put("msg", "新闻封面图片必须上传！");
			return ret;
		}
		if(StringUtils.isEmpty(news.getAuthor())){
			ret.put("type", "error");
			ret.put("msg", "新闻作者不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(news.getContent())){
			ret.put("type", "error");
			ret.put("msg", "新闻内容不能为空！");
			return ret;
		}
		news.setCreateTime(new Date());
		if(newsService.add(news) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}
	
	
	/**
	 * Description:新闻编辑页面
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:23
	 */
	@RequestMapping(value="/edit",method= RequestMethod.GET)
	public ModelAndView edit(ModelAndView model, Long id){
		model.addObject("newsCategoryList", newsCategoryService.findAll());
		model.addObject("news", newsService.find(id));
		model.setViewName("news/edit");
		return model;
	}
	
	/**
	 * Description:新闻信息编辑
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:23
	 */
	@RequestMapping(value="/edit",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,String> edit(News news){
		Map<String,String> ret = new HashMap<String, String>();
		if(news == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的信息！");
			return ret;
		}
		if(StringUtils.isEmpty(news.getTitle())){
			ret.put("type", "error");
			ret.put("msg", "新闻标题不能为空！");
			return ret;
		}
		if(news.getCategoryId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择新闻分类！");
			return ret;
		}
		if(StringUtils.isEmpty(news.getAbstrs())){
			ret.put("type", "error");
			ret.put("msg", "新闻摘要不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(news.getTags())){
			ret.put("type", "error");
			ret.put("msg", "新闻标签不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(news.getPhoto())){
			ret.put("type", "error");
			ret.put("msg", "新闻封面图片必须上传！");
			return ret;
		}
		if(StringUtils.isEmpty(news.getAuthor())){
			ret.put("type", "error");
			ret.put("msg", "新闻作者不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(news.getContent())){
			ret.put("type", "error");
			ret.put("msg", "新闻内容不能为空！");
			return ret;
		}
		if(newsService.edit(news) <= 0){
			ret.put("type", "error");
			ret.put("msg", "修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功！");
		return ret;
	}
	
	/**
	 * Description:删除新闻
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:23
	 */
	@RequestMapping(value="/delete",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(Long id){
		Map<String,String> ret = new HashMap<String, String>();
		if(id == null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的信息！");
			return ret;
		}
		try{
			if(newsService.delete(id) <= 0){
				ret.put("type", "error");
				ret.put("msg", "删除失败，请联系管理员！");
				return ret;
			}
		}catch(Exception e){
			ret.put("type", "error");
			ret.put("msg", "该新闻下有评论信息，不可删除！");
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
	 * @date 2020/2/29 14:23
	 */
	@RequestMapping(value="/list",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getList(
			@RequestParam(name="title",required=false,defaultValue="") String title,
			@RequestParam(name="author",required=false,defaultValue="") String author,
			@RequestParam(name="categoryId",required=false) Long categoryId,
			Page page
			){
		Map<String,Object> ret = new HashMap<String, Object>();
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("title", title);
		queryMap.put("author", author);
		if(categoryId != null && categoryId.longValue() != -1){
			queryMap.put("categoryId", categoryId);
		}
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", newsService.findList(queryMap));
		ret.put("total", newsService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * Description:上传图片
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:23
	 */
	@RequestMapping(value="/upload_photo",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, String> uploadPhoto(MultipartFile photo, HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(photo == null){
			ret.put("type", "error");
			ret.put("msg", "选择要上传的文件！");
			return ret;
		}
		if(photo.getSize() > 1024*1024*1024){
			ret.put("type", "error");
			ret.put("msg", "文件大小不能超过10M！");
			return ret;
		}
		//获取文件后缀
		String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")+1,photo.getOriginalFilename().length());
		if(!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())){
			ret.put("type", "error");
			ret.put("msg", "请选择jpg,jpeg,gif,png格式的图片！");
			return ret;
		}
		String savePath = request.getServletContext().getRealPath("/") + "/static/upload/";
		File savePathFile = new File(savePath);
		if(!savePathFile.exists()){
			//若不存在改目录，则创建目录
			savePathFile.mkdir();
		}
		Date date=new Date();//此时date为当前的时间
		SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");//设置当前时间的格式，为年-月-日
		String filename = dateFormat.format(date)+"."+suffix;
		try {
			//将文件保存至指定目录
			photo.transferTo(new File(savePath+filename));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			ret.put("type", "error");
			ret.put("msg", "保存文件异常！");
			e.printStackTrace();
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "用户上传图片成功！");
		ret.put("filepath",request.getServletContext().getContextPath() + "/static/upload/" + filename );
		return ret;
	}
}
