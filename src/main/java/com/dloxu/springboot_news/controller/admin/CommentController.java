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
 * Description:�������ۿ�����
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
	 * Description:���������б�ҳ��
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
	 * Description:�����������
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
			ret.put("msg", "����д��ȷ��������Ϣ��");
			return ret;
		}
		if(comment.getNewsId() == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫ���۵����ţ�");
			return ret;
		}
		if(StringUtils.isEmpty(comment.getNickname())){
			ret.put("type", "error");
			ret.put("msg", "�����ǳƲ���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(comment.getContent())){
			ret.put("type", "error");
			ret.put("msg", "�������ݲ���Ϊ�գ�");
			return ret;
		}
		comment.setCreateTime(new Date());
		if(commentService.add(comment) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�������ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��ӳɹ���");
		//��������������1
		newsService.updateCommentNumber(comment.getNewsId());
		return ret;
	}
	
	/**
	 * Description:����������Ϣ�༭
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
			ret.put("msg", "����д��ȷ��������Ϣ��");
			return ret;
		}
		if(comment.getNewsId() == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫ���۵����ţ�");
			return ret;
		}
		if(StringUtils.isEmpty(comment.getNickname())){
			ret.put("type", "error");
			ret.put("msg", "�����ǳƲ���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(comment.getContent())){
			ret.put("type", "error");
			ret.put("msg", "�������ݲ���Ϊ�գ�");
			return ret;
		}
		if(commentService.edit(comment) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�����޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�޸ĳɹ���");
		return ret;
	}
	
	/**
	 * Description:ɾ����������
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
			ret.put("msg", "��ѡ��Ҫɾ����������Ϣ��");
			return ret;
		}
		if(ids.contains(",")){
			ids = ids.substring(0,ids.length()-1);
		}
		if(commentService.delete(ids) <= 0){
			ret.put("type", "error");
			ret.put("msg", "����ɾ��ʧ�ܣ�����ϵ����Ա��");
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
