package com.katey2658.wechatserver.enums;

/**
 * Created by 11456 on 2017/3/19.
 */
public enum MessageEnums {

    ;
    /**
     * 状态码
     */
    private Short state;

    /**
     * 信息
     */
    private String message;

     MessageEnums(Short state, String message) {
        this.state = state;
        this.message = message;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
