package com.katey2658.wechatserver.config.web;

import com.katey2658.wechatserver.manager.WebSocketMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 关于websocket的配置类
 * Created by 11456 on 2017/3/6.
 */
@Configuration
@EnableWebSocket
@ComponentScan(basePackages = {"com.katey2658.wechatserver.manager"})
public class WebSocketConfig implements WebSocketConfigurer {

    /**
     * 注册关于WebSocket的处理器
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //注册并且添加映射路劲,并启用sockjs来防止客户端不能使用websocketjs,然后会选用备选方案
        registry.addHandler(getMessageHandler(),"/wechatserver/message");
               // .withSockJS();
    }

    /**
     * 创建一个新的websockethandler 并且放进ioc容器中
     * @return
     */
    @Bean
    public WebSocketMessageHandler getMessageHandler(){
        return new WebSocketMessageHandler();
    }
}
