package com.dloxu.springboot_news.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description:
 *
 * @author :  dloxu
 * @version : 1.0
 * @package com.dloxu.springboot_news.interceptor
 * @date 2020/3/15 16:40
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    /**
     * 注册拦截器，添加拦截路径跟不拦截路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截的对象会进入这个类中进行判断
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        //所有路径都被拦截
        registration.addPathPatterns("/admin/**","/system/*");
        //添加不拦截路径
        registration.excludePathPatterns("/system/login","/system/get_cpacha","/static/**");
    }

//    /**
//     * 添加静态资源文件，外部可以直接访问地址
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//    }
}