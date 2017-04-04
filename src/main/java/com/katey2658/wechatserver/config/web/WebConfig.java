package com.katey2658.wechatserver.config.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Set;

/**
 * web相关的配置类
 * Created by 11456 on 2017/3/6.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.katey2658.wechatserver.controller",
        "com.katey2658.wechatserver.api"})
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware{

    private ApplicationContext applicationContext;
    /**
     * 配置视图解析器
     * @return
     */
    /*@Bean
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

    */
    /*@Bean
    public FreeMarkerConfigurer getFreeMarkerConfig(){
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/template/");
        configurer.setDefaultEncoding("UTF-8");
        return configurer;
    }
    */

    /**
     * 将逻辑视图名称解析为Thyleaf视图解析器
     * @return
     */
    @Bean
    public ViewResolver viewResolver(TemplateEngine templateEngine){
        ThymeleafViewResolver viewResolver=new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setOrder(1);
       //viewResolver.setViewNames(new String[]{".html",".xhtml"});
        return viewResolver;
    }

    /**
     * 处理模板并渲染结果
     * @return
     */
    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine=new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    /**
     * 加载Thymeleaf模板
     * @return
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver=new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/template/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(true);
        return templateResolver;
    }

    /**
     * 配置将对静态资源的请求发给Servlet容器中默认的Servlet中进行处理
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
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
