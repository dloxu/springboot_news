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
 * Description:后台登录拦截器
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:36
 */
public class LoginInterceptor implements HandlerInterceptor {


	/**
	 * Description:afterCompletion是在渲染视图完成之后使用
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
	 * Description:postHandler在调用Controller方法之后、视图渲染之前调用
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
	 * Description: preHandle是在请求controllor前调用
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
			//表示未登录或者登录失效
			System.out.println("链接"+requestURI+"进入拦截器！");
			String header = request.getHeader("X-Requested-With");
			//判断是否是ajax请求
			if("XMLHttpRequest".equals(header)){
				//表示是ajax请求
				Map<String, String> ret = new HashMap<String, String>();
				ret.put("type", "error");
				ret.put("msg", "登录会话超时或还未登录，请重新登录!");
//				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			//表示是普通链接跳转，直接重定向到登录页面
			response.sendRedirect(request.getServletContext().getContextPath() + "/system/login");
			return false;
		}
		//获取菜单id
		String mid = request.getParameter("_mid");
		if(!StringUtils.isEmpty(mid)){
			List<Menu> allThirdMenu = MenuUtil.getAllThirdMenu((List<Menu>)request.getSession().getAttribute("userMenus"), Long.valueOf(mid));
			request.setAttribute("thirdMenuList", allThirdMenu);
		}
		return true;
	}

}
