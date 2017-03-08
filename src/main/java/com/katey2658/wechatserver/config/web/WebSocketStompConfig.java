package com.katey2658.wechatserver.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 关于Websocket的Stomp配置
 * Created by 11456 on 2017/3/8.
 */
@Configuration
@EnableWebSocketMessageBroker //启用stomp消息,表明了这个配置类不仅配置了WebSocket,还配置了基于代理的Stomp消息
public class WebSocketStompConfig extends AbstractWebSocketMessageBrokerConfigurer {
    /**
     * 注册Stomp端点
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //添加路径并启用SockJS
        registry.addEndpoint("/chatmessage").withSockJS();
    }

    /**
     * 消息代理的配置:如果不配置会自动简单的配置一个内存消息代理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //启用stomp代理中继功能，根据选择的代理不同，可选的前缀也会有所限制
        registry.enableStompBrokerRelay("/queue","/topic");
//                .setRelayHost()
//                .setRelayPort()
//                .setClientLogin()
//                .setClientPasscode();
        //发往应用程序的消息将会带有前缀
        registry.setApplicationDestinationPrefixes("/app");

    }
}
