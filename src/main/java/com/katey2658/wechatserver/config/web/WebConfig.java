package com.katey2658.wechatserver.config.web;

import freemarker.template.utility.XmlEscape;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * web相关的配置类
 * Created by 11456 on 2017/3/6.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.katey2658.wechatserver.controller"})
public class WebConfig extends WebMvcConfigurerAdapter{

    /**
     * 配置视图解析器
     * @return
     */
    @Bean
    public ViewResolver getViewResolver(){
        FreeMarkerViewResolver viewResolver=new FreeMarkerViewResolver();
        viewResolver.setContentType("text/html;charset=UTF-8");
        //设置前缀
        viewResolver.setPrefix("");
        //设置开启和缓存
        viewResolver.setCache(true);
        //设置后缀
        viewResolver.setSuffix(".ftl");
        //是的应用上下文中的bean可以通过JSTL表达式来访问
        //viewResolver.setViewClass(JstlView.class);
        //viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }

    @Bean
    public FreeMarkerConfigurer getFreeMarkerConfig(){
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/template/");
        configurer.setDefaultEncoding("UTF-8");
        return configurer;
    }


    /**
     * 配置将对静态资源的请求发给Servlet容器中默认的Servlet中进行处理
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //父类是一个空方法
        //添加拦截器
        //registry.addInterceptor()
        super.addInterceptors(registry);
    }
}
