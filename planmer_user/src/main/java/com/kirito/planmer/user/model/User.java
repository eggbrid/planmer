package com.kirito.planmer.user.model;

import kirito.peoject.baselib.mvp.BaseM;

/**
 * @auther kirito
 * @Date 2019-05-24
 * @NOTE 类说明
 */
public class User extends BaseM {
    private String id;
    private String userName;
    private String passWord;
    private String invitationCode;
    private String token;

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
