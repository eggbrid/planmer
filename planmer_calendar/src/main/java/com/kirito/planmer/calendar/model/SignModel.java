package com.kirito.planmer.calendar.model;

import kirito.peoject.baselib.mvp.BaseM;

/**
 * @auther kirito
 * @Date 2019-06-07
 * @NOTE 类说明
 */
public class SignModel extends BaseM {
    private int id;
    private int days;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
