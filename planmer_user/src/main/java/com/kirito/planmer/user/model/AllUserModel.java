package com.kirito.planmer.user.model;

import kirito.peoject.baselib.mvp.BaseM;

/**
 * @auther kirito
 * @Date 2019-06-12
 * @NOTE 类说明
 */
public class AllUserModel extends BaseM {
    public User user;
    public UserInfoModel userInfo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserInfoModel getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoModel userInfo) {
        this.userInfo = userInfo;
    }
}
