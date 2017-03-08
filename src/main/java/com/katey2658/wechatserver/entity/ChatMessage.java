package com.katey2658.wechatserver.entity;

import java.time.Instant;
import java.util.List;

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
    private Long  chatSessionId;

    /**
     * 来自用户Id
     */
    private Integer fromUserId;

    /**
     * 接收者组用户Id
     */
    private List<Integer> toUserIds;

    /**
     * 状态：1.已读，0.未读  -1.涉嫌非法
     */
    private Short status;

    /**
     * 消息文本内容
     */
    private String messageText;

    /**
     * 二进制文件
     */
    private String messageBinaryFileUrl;

    /**
     * 创建时间（必备）
     */
    private Instant gmtCreate;

    /**
     * 修改时间（必备）
     */
    private Instant gmtModified;
}
