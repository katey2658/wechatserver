package com.katey2658.wechatserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by 11456 on 2017/3/6.
 */

@Controller
public class HomeController {

    @Autowired
    private TemplateEngine thymeleaf;

    @Autowired
    private JavaMailSender mailSender;
    /**
     * 做一个简单测试，关于用户获取首页
     * @param model
     * @return
     */
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String getHomePage( Model model, RedirectAttributes redirectAttributes){
        model.addAttribute("username","王东东");
        redirectAttributes.addFlashAttribute("hahha","jajj");
        return "homepage";
    }


    @RequestMapping(value = "/mail",method = RequestMethod.GET)
    public String forHomepage() throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true,"UTF-8");
        Context context=new Context();
        context.setVariable("username","katey2658");
        context.setVariable("paragraph1","新的一年已经到来");
        context.setVariable("paragraph2","希望我们能够共同进步，加油！");
        String emailText=thymeleaf.process("notificationTemp.html",context);
        helper.setFrom("1145690747@qq.com");
        helper.setTo("katey2658@gmail.com");
        helper.setText(emailText,true);
        helper.setSubject("购一家：开启春天的开始");
        mailSender.send(mimeMessage);
        return "homepage";
    }



    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setThymeleaf(SpringTemplateEngine thymeleaf) {
        this.thymeleaf = thymeleaf;
    }
}
