package com.katey2658.wechatserver.config.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.Collections;
import java.util.Properties;

/**
 * Created by 11456 on 2017/4/4.
 */
@Configuration
@PropertySource("classpath:mail.properties")
public class SpringMailConfig implements ApplicationContextAware,EnvironmentAware{

    public static final String EMAIL_TEMPLATE_ENCODING="UTF-8";
    private static final String JAVA_MAIL_FILE="classpath:mail/javamail.properties";

    private static final String HOST="spring.mail.server.host";
    private static final String PORT="spring.mail.server.port";
    private static final String PROTOCOL="spring.mail.server.protocol";
    private static final String USERNAME="spring.mail.server.username";
    private static final String PASSWORD="spring.mail.server.password";

    private Environment env;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env=environment;
    }

    /**
     * 提供邮件服务
     * @return
     */
    @Bean
    public JavaMailSender mailSender(){
        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty(HOST));
        mailSender.setUsername(env.getProperty(USERNAME));
        mailSender.setPassword(env.getProperty(PASSWORD));
        mailSender.setDefaultEncoding(EMAIL_TEMPLATE_ENCODING);
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


    @Bean
    public ResourceBundleMessageSource emailMessageSource(){
        final ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
        messageSource.setBasename("mail/MailMessage");
        return messageSource;
    }

    @Bean
    public TemplateEngine emailTemplateEngine(){
        final SpringTemplateEngine templateEngine=new SpringTemplateEngine();
        templateEngine.addTemplateResolver(textTemplateResolver());
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        templateEngine.addTemplateResolver(stringTemplateResolver());
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
        return templateEngine;
    }

    private ITemplateResolver textTemplateResolver(){
        final ClassLoaderTemplateResolver templateResolver=new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(1));
        templateResolver.setResolvablePatterns(Collections.singleton("text/*"));
        templateResolver.setPrefix("/mail/");
        templateResolver.setSuffix(".text");
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    private ITemplateResolver htmlTemplateResolver(){
        final ClassLoaderTemplateResolver templateResolver=new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(2));
        templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
        templateResolver.setPrefix("/mail/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    private ITemplateResolver stringTemplateResolver(){
        final StringTemplateResolver templateResolver=new StringTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(3));
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        return templateResolver;
    }
}
