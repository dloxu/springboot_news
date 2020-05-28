package com.dloxu.springboot_news.interceptor;

import com.dloxu.springboot_news.model.Menu;
import com.dloxu.springboot_news.util.MenuUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:��̨��¼������
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:36
 */
public class LoginInterceptor implements HandlerInterceptor {


	/**
	 * Description:afterCompletion������Ⱦ��ͼ���֮��ʹ��
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/3/15 16:46
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * Description:postHandler�ڵ���Controller����֮����ͼ��Ⱦ֮ǰ����
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/3/15 16:46
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}


	/**
	 * Description: preHandle��������controllorǰ����
	 * @author   dloxu
	 * @param
	 * @return
	 * @date 2020/3/15 16:46
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String requestURI = request.getRequestURI();
		Object admin = request.getSession().getAttribute("admin");
		if(admin == null){
			//��ʾδ��¼���ߵ�¼ʧЧ
			System.out.println("����"+requestURI+"������������");
			String header = request.getHeader("X-Requested-With");
			//�ж��Ƿ���ajax����
			if("XMLHttpRequest".equals(header)){
				//��ʾ��ajax����
				Map<String, String> ret = new HashMap<String, String>();
				ret.put("type", "error");
				ret.put("msg", "��¼�Ự��ʱ��δ��¼�������µ�¼!");
//				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			//��ʾ����ͨ������ת��ֱ���ض��򵽵�¼ҳ��
			response.sendRedirect(request.getServletContext().getContextPath() + "/system/login");
			return false;
		}
		//��ȡ�˵�id
		String mid = request.getParameter("_mid");
		if(!StringUtils.isEmpty(mid)){
			List<Menu> allThirdMenu = MenuUtil.getAllThirdMenu((List<Menu>)request.getSession().getAttribute("userMenus"), Long.valueOf(mid));
			request.setAttribute("thirdMenuList", allThirdMenu);
		}
		return true;
	}

}
