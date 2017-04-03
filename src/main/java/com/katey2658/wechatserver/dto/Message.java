package com.katey2658.wechatserver.dto;

/**
 * Created by 11456 on 2017/3/19.
 */


/**
 * 远程API调用的时候，返回的消息格式
 */
public class Message {
    /**
     * 返回的状态码
     */
    private Short state;

    /**
     * 错误的提示信息
     */
    private String error;

    /**
     * 请求的资源
     */
    private Object data;

    /**
     * 失败之后返回的信息
     * @param state
     * @param error
     */
    public Message(Short state, String error) {
        this.state = state;
        this.error = error;
    }

    /**
     * 请求成功返回
     * @param state
     * @param data
     */
    public Message(Short state, Object data) {
        this.state = state;
        this.data = data;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
