package com.katey2658.wechatserver.domain;

import java.time.Instant;

/**
 * 用户类
 * Created by 11456 on 2017/3/8.
 */
public class User {
    /**
     * 用户Id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户手机号
     */
    private String telephone;
    /**
     * QRCode二维码
     */
    private String qrCode;
    /**
     * 用户邮箱
     */
    private String userMail;
    /**
     * 用户密码：32位MD5加密码
     */
    private String password;
    /**
     * 用户状态:1 正常使用,0 暂未激活 -1 禁用
     */
    private Short status;
    /**
     * 创建时间
     */
    private Instant gmtCreate;
    /**
     * 最后修改时间
     */
    private Instant gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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
