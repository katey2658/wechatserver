package com.katey2658.wechatserver.dto;

/**
 * Created by 11456 on 2017/3/22.
 */

/**
 * 聊天建立连接的返回数据
 */
public class ConnectionMessage {

    /**
     * 返回类型
     */
    private Short type;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 如果失败类之后返回i的信息提示
     */
    private String error;

    /**
     * 返回的数据:sessionId
     */
   private Long sessionId;

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}
