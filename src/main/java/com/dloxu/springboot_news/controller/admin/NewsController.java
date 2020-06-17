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
 * Description:���ſ�����
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
	 * Description:�����б�ҳ��
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
	 * Description:�������ҳ��
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
	 * Description:�������
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
			ret.put("msg", "����д��ȷ����Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(news.getTitle())){
			ret.put("type", "error");
			ret.put("msg", "���ű��ⲻ��Ϊ�գ�");
			return ret;
		}
		if(news.getCategoryId() == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ�����ŷ��࣡");
			return ret;
		}
		if(StringUtils.isEmpty(news.getAbstrs())){
			ret.put("type", "error");
			ret.put("msg", "����ժҪ����Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(news.getTags())){
			ret.put("type", "error");
			ret.put("msg", "���ű�ǩ����Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(news.getPhoto())){
			ret.put("type", "error");
			ret.put("msg", "���ŷ���ͼƬ�����ϴ���");
			return ret;
		}
		if(StringUtils.isEmpty(news.getAuthor())){
			ret.put("type", "error");
			ret.put("msg", "�������߲���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(news.getContent())){
			ret.put("type", "error");
			ret.put("msg", "�������ݲ���Ϊ�գ�");
			return ret;
		}
		news.setCreateTime(new Date());
		if(newsService.add(news) <= 0){
			ret.put("type", "error");
			ret.put("msg", "���ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��ӳɹ���");
		return ret;
	}
	
	
	/**
	 * Description:���ű༭ҳ��
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
	 * Description:������Ϣ�༭
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
			ret.put("msg", "����д��ȷ����Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(news.getTitle())){
			ret.put("type", "error");
			ret.put("msg", "���ű��ⲻ��Ϊ�գ�");
			return ret;
		}
		if(news.getCategoryId() == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ�����ŷ��࣡");
			return ret;
		}
		if(StringUtils.isEmpty(news.getAbstrs())){
			ret.put("type", "error");
			ret.put("msg", "����ժҪ����Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(news.getTags())){
			ret.put("type", "error");
			ret.put("msg", "���ű�ǩ����Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(news.getPhoto())){
			ret.put("type", "error");
			ret.put("msg", "���ŷ���ͼƬ�����ϴ���");
			return ret;
		}
		if(StringUtils.isEmpty(news.getAuthor())){
			ret.put("type", "error");
			ret.put("msg", "�������߲���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(news.getContent())){
			ret.put("type", "error");
			ret.put("msg", "�������ݲ���Ϊ�գ�");
			return ret;
		}
		if(newsService.edit(news) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�޸ĳɹ���");
		return ret;
	}
	
	/**
	 * Description:ɾ������
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
			ret.put("msg", "��ѡ��Ҫɾ������Ϣ��");
			return ret;
		}
		try{
			if(newsService.delete(id) <= 0){
				ret.put("type", "error");
				ret.put("msg", "ɾ��ʧ�ܣ�����ϵ����Ա��");
				return ret;
			}
		}catch(Exception e){
			ret.put("type", "error");
			ret.put("msg", "����������������Ϣ������ɾ����");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "ɾ���ɹ���");
		return ret;
	}
	
	/**
	 * Description:��ҳģ��������ѯ�б�
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
	 * Description:�ϴ�ͼƬ
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
			ret.put("msg", "ѡ��Ҫ�ϴ����ļ���");
			return ret;
		}
		if(photo.getSize() > 1024*1024*1024){
			ret.put("type", "error");
			ret.put("msg", "�ļ���С���ܳ���10M��");
			return ret;
		}
		//��ȡ�ļ���׺
		String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")+1,photo.getOriginalFilename().length());
		if(!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��jpg,jpeg,gif,png��ʽ��ͼƬ��");
			return ret;
		}
		String savePath = request.getServletContext().getRealPath("/") + "/static/upload/";
		File savePathFile = new File(savePath);
		if(!savePathFile.exists()){
			//�������ڸ�Ŀ¼���򴴽�Ŀ¼
			savePathFile.mkdir();
		}
		Date date=new Date();//��ʱdateΪ��ǰ��ʱ��
		SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");//���õ�ǰʱ��ĸ�ʽ��Ϊ��-��-��
		String filename = dateFormat.format(date)+"."+suffix;
		try {
			//���ļ�������ָ��Ŀ¼
			photo.transferTo(new File(savePath+filename));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			ret.put("type", "error");
			ret.put("msg", "�����ļ��쳣��");
			e.printStackTrace();
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�û��ϴ�ͼƬ�ɹ���");
		ret.put("filepath",request.getServletContext().getContextPath() + "/static/upload/" + filename );
		return ret;
	}
}
