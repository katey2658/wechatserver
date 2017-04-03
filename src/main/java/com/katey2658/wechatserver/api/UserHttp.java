package com.katey2658.wechatserver.api;

import com.katey2658.wechatserver.domain.User;
import com.katey2658.wechatserver.dto.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 11456 on 2017/3/19.
 */

/**
 * 用户服务对外开放接口
 */
@Controller
@ResponseBody
@RequestMapping("/api.webchatserver/users")
public class UserHttp {

    @RequestMapping(value = "/me",method = RequestMethod.GET)
    public User login(User user){
        return user;
    }

    /**
     * 获得自己的信息
     * @return
     */
    @GetMapping("/{userId}/me")
    public Message getInfo(@PathVariable("userId")Integer userId){
        User user=new User();
        user.setId(userId);
        Message message=new Message((short)200,user);
        return message;
    }
}
