package com.katey2658.wechatserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;

/**
 * Created by 11456 on 2017/3/6.
 */

@Controller
public class HomeController {
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

}
