package com.kirito.planmer.calendar.model;

import kirito.peoject.baselib.mvp.BaseM;

/**
 * @auther kirito
 * @Date 2019-05-21
 * @NOTE 类说明
 */
public class HomeData extends BaseM {
    private TaskModel task;
    private int isTodaySign;//0未签到 1已签到

    private SignModel sign;


    public TaskModel getTask() {
        return task;
    }

    public void setTask(TaskModel task) {
        this.task = task;
    }

    public int getIsTodaySign() {
        return isTodaySign;
    }

    public void setIsTodaySign(int isTodaySign) {
        this.isTodaySign = isTodaySign;
    }

    public SignModel getSign() {
        return sign;
    }

    public void setSign(SignModel sign) {
        this.sign = sign;
    }
}
