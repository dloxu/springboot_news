package com.dloxu.springboot_news.controller.admin;


import com.dloxu.springboot_news.model.NewsCategory;
import com.dloxu.springboot_news.model.Page;
import com.dloxu.springboot_news.service.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:���ŷ��������
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:20
 */
@RequestMapping("/admin/news_category")
@Controller
public class NewsCategoryController {
	
	@Autowired
	private NewsCategoryService newsCategoryService;
	
	/**
	 * Description:���ŷ����б�ҳ��
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:20
	 */
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("news_category/list");
		return model;
	}
	
	/**
	 * Description:���ŷ������
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:20
	 */
	@RequestMapping(value="/add",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,String> add(NewsCategory newsCategory){
		Map<String,String> ret = new HashMap<String, String>();
		if(newsCategory == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ�ķ�����Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(newsCategory.getName())){
			ret.put("type", "error");
			ret.put("msg", "�������Ʋ���Ϊ�գ�");
			return ret;
		}
		if(newsCategoryService.add(newsCategory) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�������ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��ӳɹ���");
		return ret;
	}
	
	/**
	 * Description:���ŷ�����Ϣ�༭
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:20
	 */
	@RequestMapping(value="/edit",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,String> edit(NewsCategory newsCategory){
		Map<String,String> ret = new HashMap<String, String>();
		if(newsCategory == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ�ķ�����Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(newsCategory.getName())){
			ret.put("type", "error");
			ret.put("msg", "�������Ʋ���Ϊ�գ�");
			return ret;
		}
		if(newsCategoryService.edit(newsCategory) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�����޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�޸ĳɹ���");
		return ret;
	}
	
	/**
	 * Description:ɾ�����ŷ���
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:20
	 */
	@RequestMapping(value="/delete",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(Long id){
		Map<String,String> ret = new HashMap<String, String>();
		if(id == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ���ķ�����Ϣ��");
			return ret;
		}
		try{
			if(newsCategoryService.delete(id) <= 0){
				ret.put("type", "error");
				ret.put("msg", "����ɾ��ʧ�ܣ�����ϵ����Ա��");
				return ret;
			}
		}catch(Exception e){
			ret.put("type", "error");
			ret.put("msg", "�÷�������������Ϣ������ɾ����");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�޸ĳɹ���");
		return ret;
	}
	
	/**
	 * Description:��ҳģ��������ѯ�б�
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/2/29 14:21
	 */
	@RequestMapping(value="/list",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getList(
			@RequestParam(name="name",required=false,defaultValue="") String name,
			Page page
			){
		Map<String,Object> ret = new HashMap<String, Object>();
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", newsCategoryService.findList(queryMap));
		ret.put("total", newsCategoryService.getTotal(queryMap));
		return ret;
	}
}
