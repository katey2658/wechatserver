package com.katey2658.wechatserver.config;

import com.katey2658.wechatserver.config.dao.RootDaoConfig;
import com.katey2658.wechatserver.config.service.RootServiceConfig;
import com.katey2658.wechatserver.config.web.WebConfig;
import com.katey2658.wechatserver.config.web.WebSecurityConfig;
import com.katey2658.wechatserver.config.web.WebSocketConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * 主要的配置类，是整个应用的入口类
 * Created by 11456 on 2017/3/6.
 */
public class WebApplicationConfig
        extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 配置除web其它的配置类
     * @return
     */
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ RootServiceConfig.class};
    }

    /**
     * 配置web的配置类
     * @return
     */
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class, WebSocketConfig.class, WebSecurityConfig.class};
    }

    /**
     * 设置路径
     * @return
     */
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 配置过滤器
     * @return
     */
    @Override
    protected javax.servlet.Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter=new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return super.getServletFilters();
    }

    /**
     * dispatcher相关配置
     * @param registration
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        //配置上传文件的临时存储目录
        registration.setMultipartConfig(
                new MultipartConfigElement("/temp/upload"));
        //添加对DWR的映射
        registration.addMapping("/dwr/*");
    }
}
