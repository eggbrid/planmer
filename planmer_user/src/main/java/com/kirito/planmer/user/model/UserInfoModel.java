package com.kirito.planmer.user.model;

import kirito.peoject.baselib.mvp.BaseM;

/**
 * @auther kirito
 * @Date 2019-06-12
 * @NOTE 类说明
 */
public class UserInfoModel extends BaseM {
    public int id;
    public String nickName;
    public String avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
