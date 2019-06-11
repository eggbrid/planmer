package com.kirito.planmer.calendar.fragment;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import androidx.annotation.NonNull;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.kirito.planmer.calendar.R;
import com.kirito.planmer.calendar.activity.AddTaskActivity;
import com.kirito.planmer.calendar.presenter.SignP;
import com.kirito.planmer.calendar.presenter.TaskP;
import com.kirito.planmer.calendar.view.widget.MyTaurusHeader;
import com.kirito.planmer.calendar.view.widget.TaskCardView;
import com.kirito.planmer.calendar.view.widget.ToDayFragmentView;
import com.scwang.smartrefresh.header.*;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.study.xuan.shapebuilder.shape.ShapeBuilder;
import kirito.peoject.baselib.UI.BaseFragment;
import kirito.peoject.baselib.mvp.BaseP;
import kirito.peoject.baselib.util.IntentUtil;
import kirito.peoject.baselib.util.SizeUtils;
import kirito.peoject.baselib.util.ThreadUtils;
import kirito.peoject.constantlibs.UIConstant.activity.CalendarLibs;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * @auther kirito
 * @Date 2019-05-21
 * @NOTE 类说明
 */

public class ToDayFragment extends BaseFragment<ToDayFragmentView> implements View.OnClickListener , TaskCardView.OnStatusChangeListsner {
    private CountDownTimer countDownTimer;

    public long times;
    public long dtimes;

    public int task_info_id;

    @Override
    public void afterInitView(final ToDayFragmentView view) {
        view.taskCardView.setOnStatusChangeListsner(this);
        view.tvBtnSign.setOnClickListener(this);
        view.rlNoTask.setOnClickListener(this);
        view.rlNoTask.setVisibility(View.VISIBLE);
        view.srlRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });
        view.srlRefresh.setRefreshHeader(new MyTaurusHeader(activity));
        getData();

    }

    public void getData() {
        getP(TaskP.class).getHome( view);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == view.tvBtnSign.getId()) {
            getP(SignP.class).add(view.tvDay, view.tvBtnSign);
        } else if (v.getId() == view.rlNoTask.getId()) {
            IntentUtil.startActivity(activity, AddTaskActivity.class);
        }
    }




    @Override
    public void onStatusChange(int status, int id) {
getP(TaskP.class).changeStatus(view,status,id);
    }


}
