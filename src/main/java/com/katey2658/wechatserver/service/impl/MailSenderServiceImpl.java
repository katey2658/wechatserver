package com.katey2658.wechatserver.service.impl;

import com.katey2658.wechatserver.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

/**
 * Created by 11456 on 2017/4/3.
 */
@Service
public class MailSenderServiceImpl implements MailSenderService {



    public void sendNotificaton() {
        Context context=new Context();
        context.setVariable("username","katey2658");
        context.setVariable("paragraph1","");
        context.setVariable("paragraph1","");
    }
}
