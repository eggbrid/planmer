package com.kirito.planmer.calendar.fragment;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.kirito.planmer.calendar.R;
import com.kirito.planmer.calendar.view.widget.ToDayFragmentView;
import com.study.xuan.shapebuilder.shape.ShapeBuilder;
import kirito.peoject.baselib.UI.BaseFragment;
import kirito.peoject.baselib.util.SizeUtils;
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

    @Override
    public void afterInitView(ToDayFragmentView view) {
        view.rlStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == view.rlStart.getId()) {
            if (isStarted) {
                stopTask();
            } else {
                startTask();

            }
            isStarted = !isStarted;
        }
    }

    public void startTask() {
        initCountDownTimer(5);
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
        countDownTimer.cancel();
        ShapeBuilder.create()
                .Radius(SizeUtils.dp2px(5))
                .Type(RECTANGLE)
                .Solid(getResources().getColor(R.color.app_color_light,null))
                .build(view.rlTaskBg);
    }

    /**
     * 初始化倒计时的类
     */
    private void initCountDownTimer(final long all) {
        countDownTimer = new CountDownTimer(all * 1000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                long min = millisUntilFinished / (1000 * 60);
                long s = (millisUntilFinished - min * 1000 * 60) / 1000;
                view.tvRemainingTime.setText((min == 0 ? "" : min + "m ") + s + "s");
                int d= (int) (234*(1-(double)millisUntilFinished/(all*1000)));
                Log.e("wangxu", "d="+d);
                int g;
                int b=0;
                if (d<57){
                    g=d;
                }else{
                    g=57;
                    b=d-57;
                }


                ShapeBuilder.create()
                        .Radius(SizeUtils.dp2px(5))
                        .Type(RECTANGLE)
                        .Solid(Color.argb(255,73,178+g, 253-b))
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
                        .Solid(getResources().getColor(R.color.color_finish,null))
                        .build(view.rlTaskBg);
            }
        };
    }

}
