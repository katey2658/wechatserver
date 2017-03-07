package com.katey2658.wechatserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 11456 on 2017/3/6.
 */

@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * 做一个简单测试，关于用户获取首页
     * @param model
     * @return
     */
    @RequestMapping("/home")
    public String getHomePage(Model model){
        model.addAttribute("username","王东东");
        return "homepage";
    }
}
