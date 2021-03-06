package com.kirito.planmer.calendar.activity;

import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.kirito.planmer.calendar.presenter.TaskP;
import com.kirito.planmer.calendar.view.widget.task.AddTaskActivityView;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.constantlibs.UIConstant.activity.CalendarLibs;

/**
 * @auther kirito
 * @Date 2019-06-06
 * @NOTE 类说明
 */
@Route(path = CalendarLibs.ACTIVITY_ADD_TASK)
public class AddTaskActivity extends BaseActivity<AddTaskActivityView> implements AddTaskActivityView.AddTaskListener {

    public long startTime = -1;
    public int day = -1;
    public long dTime = -1;

    @Override
    public void afterInitView(AddTaskActivityView v) {
        super.afterInitView(v);
        view.setAddTaskListener(this);
        view.mTvDay.setOnClickListener(this);
        view.mTvDTime.setOnClickListener(this);
        view.mTvStartTime.setOnClickListener(this);
        view.tvSubmit.setOnClickListener(this);
view.mTitle.setNormalTitle(this,"添加计划");
    }

    @Override
    public void onStartTimeSelect(long time) {
        startTime = time;
    }

    @Override
    public void onDaySelect(int time) {
        day = time;
    }

    @Override
    public void onDtimeSelect(long time) {
        dTime = time;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == view.mTvDay.getId()) {
            view.day.show();

        } else if (v.getId() == view.mTvDTime.getId()) {
            view.dDtime.show();

        } else if (v.getId() == view.mTvStartTime.getId()) {
            view.startTime.show();

        } else if (v.getId() == view.tvSubmit.getId()) {
            getP(TaskP.class).add(this);
        }
    }
}
