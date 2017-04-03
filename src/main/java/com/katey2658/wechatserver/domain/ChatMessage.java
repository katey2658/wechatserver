package com.katey2658.wechatserver.domain;

import java.time.Instant;

/**
 * 聊天信息对象实体
 * Created by 11456 on 2017/3/8.
 */
public class ChatMessage {
    /**
     * 消息Id（必备）
     */
    private Long id;

    /**
     * 会话Id号
     */
    private Long  sessionId;

    /**
     * 来自用户Id
     */
    private Integer sender;

    /**
     * 接收者组用户Id
     */
    private Integer receiver;

    /**
     * 状态：1.已读，0.未读  -1.涉嫌非法
     */
    private Short status;

    /**
     * 消息类型：文字，语音，图片，表情，视频，链接，文件，地理信息。
     *         涉及到其它信息的如金钱交易+视频+语音等，另外
     */
    private Short dataType;

    /**
     * 消息文本内容/链接地址/地理信息
     */
    private String messageText;

    /**
     * 二进制文件URL，只保存地址
     */
    private String BinaryFileUrl;

    /**
     * 创建时间（必备）
     */
    private Instant gmtCreate;

    /**
     * 修改时间（必备）
     */
    private Instant gmtModified;


    public Short getDataType() {
        return dataType;
    }

    public void setDataType(Short dataType) {
        this.dataType = dataType;
    }

    public String getBinaryFileUrl() {
        return BinaryFileUrl;
    }

    public void setBinaryFileUrl(String binaryFileUrl) {
        BinaryFileUrl = binaryFileUrl;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Instant getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Instant gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Instant getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Instant gmtModified) {
        this.gmtModified = gmtModified;
    }
}
