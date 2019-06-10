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
import com.kirito.planmer.calendar.view.widget.ToDayFragmentView;
import com.scwang.smartrefresh.header.*;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.study.xuan.shapebuilder.shape.ShapeBuilder;
import kirito.peoject.baselib.UI.BaseFragment;
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

public class ToDayFragment extends BaseFragment<ToDayFragmentView> implements View.OnClickListener {
    private boolean isStarted = false;
    private CountDownTimer countDownTimer;

    public long times;
    public long dtimes;

    public int task_info_id;

    @Override
    public void afterInitView(final ToDayFragmentView view) {
        view.rlStart.setOnClickListener(this);
        view.tvBtnSign.setOnClickListener(this);
        view.rlNoTask.setOnClickListener(this);
        view.rlNoTask.setVisibility(View.VISIBLE);
        view.rlTaskBg.setVisibility(View.GONE);
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
        getP(TaskP.class).getHome(this, view);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == view.rlStart.getId()) {
            if (isStarted) {
                getP(TaskP.class).changeStatus(this,view,2,task_info_id);
            } else {
                getP(TaskP.class).changeStatus(this,view,1,task_info_id);
            }
            isStarted = !isStarted;
        } else if (v.getId() == view.tvBtnSign.getId()) {
            getP(SignP.class).add(view.mTvDay, view.tvBtnSign);
        } else if (v.getId() == view.rlNoTask.getId()) {
            IntentUtil.startActivity(activity, AddTaskActivity.class);
        }
    }

    public void startTask() {
        initCountDownTimer(times);
        RotateAnimation rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        rotate.setDuration(800);//设置动画持续周期
        rotate.setRepeatCount(-1);//设置重复次数
        rotate.setStartOffset(0);//执行前的等待时间
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.mBtnStart.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.mBtnStart.setImageResource(R.drawable.btn_task_stop);
                    }
                }, 200);

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.btnTaskBg.startAnimation(rotate);
        countDownTimer.start();
    }

    public void stopTask() {
        view.btnTaskBg.clearAnimation();
        view.mBtnStart.setImageResource(R.drawable.btn_task_start);
        ShapeBuilder.create()
                .Radius(SizeUtils.dp2px(5))
                .Type(RECTANGLE)
                .Solid(getResources().getColor(R.color.app_color_light, null))
                .build(view.rlTaskBg);
        if (countDownTimer!=null)
        countDownTimer.cancel();
    }

    /**
     * 初始化倒计时的类
     */
    private void initCountDownTimer(final long all) {
        countDownTimer = new CountDownTimer(all, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                long min = millisUntilFinished / (1000 * 60);
                long s = (millisUntilFinished - min * 1000 * 60) / 1000;
                view.tvRemainingTime.setText((min == 0 ? "" : min + "m ") + s + "s");
                int d = (int) (234 * (1 - (double) millisUntilFinished / (all)));
                times = millisUntilFinished;
                int g;
                int b = 0;
                if (d < 57) {
                    g = d;
                } else {
                    g = 57;
                    b = d - 57;
                }


                ShapeBuilder.create()
                        .Radius(SizeUtils.dp2px(5))
                        .Type(RECTANGLE)
                        .Solid(Color.argb(255, 73, 178 + g, 253 - b))
                        .build(view.rlTaskBg);
            }

            @Override
            public void onFinish() {
                view.btnTaskBg.clearAnimation();
                view.rlStart.setVisibility(View.GONE);
                countDownTimer.cancel();
                view.tvRemainingTime.setText("已完成");
                ShapeBuilder.create()
                        .Radius(SizeUtils.dp2px(5))
                        .Type(RECTANGLE)
                        .Solid(getResources().getColor(R.color.color_finish, null))
                        .build(view.rlTaskBg);
            }
        };
    }

}
