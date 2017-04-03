package com.katey2658.wechatserver.repository;

import com.katey2658.wechatserver.domain.ChatMessage;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * 聊天信息持久层操作接口
 * Created by 11456 on 2017/3/9.
 */
public interface ChatMessageRepository {
    /**
     * 插入一条信息
     * @param chatMessage
     * @return
     */
    int insertMessage(ChatMessage chatMessage);

    /**
     * 根据会话更新状态信息
     * @param userId
     * @param chatSessionId
     * @return
     */
    int updateStatusBySessionId(@Param("chatSessionId") long chatSessionId,
                                @Param("userId") int userId);

    /**
     * 查找聊天记录
     * @param chatSessionId 会话Id
     * @param offest 偏移量
     * @param limit  数据量
     * @return
     */
    Collection<ChatMessage> ListAllByChatSessionId(@Param("chatSessionId") long chatSessionId,
                                                   @Param("offest") int offest,
                                                   @Param("limit") int limit);
}
