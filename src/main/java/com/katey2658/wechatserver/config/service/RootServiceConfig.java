package com.katey2658.wechatserver.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 关于服务层的相关对象Bean配置
 * Created by 11456 on 2017/3/6.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.katey2658.wechatserver.service"})
public class RootServiceConfig  {


    /**
     * 配置事务管理器
     * @param dataSource
     * @return
     */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager transactionManager= new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}
