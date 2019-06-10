package com.kirito.planmer.calendar.model;

import kirito.peoject.baselib.mvp.BaseM;

/**
 * @auther kirito
 * @Date 2019-06-10
 * @NOTE 类说明
 */
public class TaskInfoModel extends BaseM {
    public int id;
    public int userId;
    public long startTime;
    public long endTime;
    public int status;
    public int taskId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
