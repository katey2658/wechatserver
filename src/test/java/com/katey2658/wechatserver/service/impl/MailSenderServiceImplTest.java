package com.katey2658.wechatserver.service.impl;

import com.katey2658.wechatserver.config.service.RootServiceConfig;
import com.katey2658.wechatserver.config.web.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by 11456 on 2017/4/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootServiceConfig.class, WebConfig.class})
public class MailSenderServiceImplTest  {

    @Autowired
    private SpringTemplateEngine thymeleaf;

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public  void textMailSender() throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
        Context context=new Context();
        context.setVariable("username","katey2658");
        context.setVariable("paragraph1","新的一年已经到来");
        context.setVariable("paragraph1","希望我们能够共同进步，加油！");
        String emailText=thymeleaf.process("notificationTemp.html",context);
        helper.setFrom("1145690747@qq.com");
        helper.setTo("katey2658@gmail.com");
        helper.setSubject("购一家：开启春天的开始");
        mailSender.send(mimeMessage);
    }

}