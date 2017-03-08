package com.katey2658.wechatserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * 控制来着客户端的消息
 * Created by 11456 on 2017/3/8.
 */
@Controller
public class MessageController {
    private Logger logger=LoggerFactory.getLogger(MessageController.class);

    /**
     * 处理该路径的消息
     * @param message
     */
    //@MessageMapping("/message")
    public  void  handleMessage(String message){
        logger.info("receive message from client:"+message);
    }


}
