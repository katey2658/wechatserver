package com.katey2658.wechatserver.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 关于服务层的相关对象Bean配置
 * Created by 11456 on 2017/3/6.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:mail.properties")
@ComponentScan(basePackages = {"com.katey2658.wechatserver.service"})
public class RootServiceConfig  {

    @Autowired
    private Environment env;

    /**
     * 配置事务管理器
     * @param dataSource
     * @return
     */
    /*@Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager transactionManager= new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
    */

    /**
     * 提供邮件服务
     * @return
     */
    @Bean
    public JavaMailSender mailSender(){
        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setPassword(env.getProperty("spring.mail.password"));
        Properties properties=new Properties();
        properties.setProperty("mail.smtp.auth"
                ,env.getProperty("spring.mail.properties.mail.smtp.auth"));
        properties.setProperty("mail.smtp.starttls.enable"
                ,env.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        properties.setProperty("smtp.starttls.required"
                ,env.getProperty("spring.mail.properties.mail.smtp.starttls.required"));
        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }
}
