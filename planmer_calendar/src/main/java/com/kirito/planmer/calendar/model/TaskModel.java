package com.kirito.planmer.calendar.model;

import kirito.peoject.baselib.mvp.BaseM;

/**
 * @auther kirito
 * @Date 2019-06-07
 * @NOTE 类说明
 */
public class TaskModel extends BaseM {

    private int id;
    private long starTime;
    private long endTime;
    private int userId;
    private long dTime;
    private String title;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStarTime() {
        return starTime;
    }

    public void setStarTime(long starTime) {
        this.starTime = starTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getdTime() {
        return dTime;
    }

    public void setdTime(long dTime) {
        this.dTime = dTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
