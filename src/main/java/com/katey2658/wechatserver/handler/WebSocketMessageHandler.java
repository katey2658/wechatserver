package com.katey2658.wechatserver.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 远程websocket连接中处理关于消息的事情
 * Created by 11456 on 2017/3/8.
 */
public class WebSocketMessageHandler extends AbstractWebSocketHandler{
    private Logger logger= LoggerFactory.getLogger(WebSocketMessageHandler.class);

    /**
     * 内部维护一个队列
     */
    private Map<String,WebSocketSession> sessionList=new HashMap<>();

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        logger.info("handlePongMessage is executed");
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        logger.info("handleBinaryMessage is executed");
    }

    /**
     * 处理发送过来的文本消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("handleTextMessage is executed"+message.getPayload());
        session.sendMessage(new TextMessage("handleTextMessage is executed"));
        //TODO 收到消息后应该将消息给予服务层进行解析，然后在给予对应的处理
        //TODO 然后通知将消息转发给消息的接收方
    }


    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        logger.info("handleMessage is executed"+message.getPayload());
        session.sendMessage(new TextMessage("handleTextMessage is executed"));

        for (String key:sessionList.keySet()) {
            if (key!=session.getId()){
                WebSocketSession targetSession=sessionList.get(key);
                targetSession.sendMessage(new TextMessage("你收到了一条信息:"+message.getPayload()));
            }
        }

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.info("handleTransportError is executed");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("afterConnectionClosed is executed");
        //TODO 当断开连接的时候丢弃
        sessionList.remove(session.getId());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //TODO 连接后应该获取参数，关于userId 和 token 并且
        Map params=session.getAttributes();
        params.get("userId");
        params.get("token");
        //TODO 需要验证合法性，验证合格后 放入集合中进行维护 ，key是 userId,值是session
        logger.info("afterConnectionEstablished is executed");
        //TODO 当建立的时候可以将sessionId放入一个队列中，然后可以通过队列来执行
        sessionList.put(session.getId(),session);
        //TODO 刚连接的时候需要从数据库中取出部分历史消息返回
    }

    @Override
    public boolean supportsPartialMessages() {
        logger.info("supportsPartialMessages is executed");
        return super.supportsPartialMessages();

    }
}
