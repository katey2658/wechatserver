package com.katey2658.wechatserver.controller;

import com.katey2658.wechatserver.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * Created by 11456 on 2017/4/4.
 */
@Controller
@RequestMapping("/mail")
public class MailSenderController {

    @Autowired
    private MailSenderService mailSenderService;

    @RequestMapping(method = RequestMethod.POST)
    public String sendTemplateContent(Locale locale){

    }

}
