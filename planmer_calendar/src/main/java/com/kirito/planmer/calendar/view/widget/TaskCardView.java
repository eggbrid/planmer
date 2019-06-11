package com.kirito.planmer.calendar.view.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.kirito.planmer.calendar.R;
import com.kirito.planmer.calendar.model.TaskInfoModel;
import com.kirito.planmer.calendar.model.TaskModel;
import com.study.xuan.shapebuilder.shape.ShapeBuilder;
import kirito.peoject.baselib.util.SizeUtils;
import kirito.peoject.baselib.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;


/**
 * @auther kirito
 * @Date 2019-06-11
 * @NOTE 类说明
 */
public class TaskCardView extends LinearLayout implements View.OnClickListener {

    private int status = 0;
    private CountDownTimer countDownTimer;
    public TextView mTvDay;
    public ImageView mBtnStart;
    public TextView mTvTask;
    public TextView mTvTaskContent;
    public TextView mTvTime;
    public ImageView btnTaskBg;
    public RelativeLayout rlStart;
    public TextView tvRemainingTime;
    public LinearLayout rlTaskBg;
    public TextView tvFinish;
    public long times;
    public long dtimes;
    public int id;
    private OnStatusChangeListsner onStatusChangeListsner;


    public void setOnStatusChangeListsner(OnStatusChangeListsner onStatusChangeListsner) {
        this.onStatusChangeListsner = onStatusChangeListsner;
    }

    public TaskCardView(Context context) {
        this(context, null);
    }

    public TaskCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setData(TaskModel taskModel) {
        dtimes = taskModel.getdTime();
        times = taskModel.getdTime();
        mTvTask.setText(taskModel.getTitle());
        mTvTaskContent.setText(taskModel.getContent());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd日");
        mTvTime.setText(TimeUtils.date2String(new Date(taskModel.getStarTime()), simpleDateFormat) + "-" +
                TimeUtils.date2String(new Date(taskModel.getEndTime()), simpleDateFormat) + " 持续" + getTimewString(dtimes));
        tvRemainingTime.setText(getTimewString(dtimes));

    }

    public void setTask(TaskInfoModel taskInfoModel) {
        this.id=taskInfoModel.getId();

        if (taskInfoModel.getStatus() == 0) {
            clearStatus(dtimes);

            //未开始

        } else if (taskInfoModel.getStatus() == 1) {
            //一开始
            times = dtimes - (taskInfoModel.getEndTime() - taskInfoModel.getStartTime());
            clearStatus(times);
            startTask(times);

        } else if (taskInfoModel.getStatus() == 2) {
            times = dtimes - (taskInfoModel.getEndTime() - taskInfoModel.getStartTime());

            clearStatus(times);
            pauseTask(times);

        } else if (taskInfoModel.getStatus() == 3) {
            stopTask();

        } else {
            finishTash();
        }
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_task_card_view, this, false);
        rlStart = view.findViewById(R.id.rlStart);
        mTvDay = view.findViewById(R.id.tvDay);
        mBtnStart = view.findViewById(R.id.btnStart);
        mTvTask = view.findViewById(R.id.tvTask);
        mTvTaskContent = view.findViewById(R.id.tvTaskContent);

        mTvTime = view.findViewById(R.id.tvTime);
        btnTaskBg = view.findViewById(R.id.btnTaskBg);
        tvRemainingTime = view.findViewById(R.id.tvRemainingTime);
        rlTaskBg = view.findViewById(R.id.rlTaskBg);
        tvFinish = view.findViewById(R.id.tvFinish);
        tvFinish.setOnClickListener(this);
        rlTaskBg.setOnClickListener(this);
        addView(view);
    }

    /**
     * 清空状态
     */
    public void clearStatus(long times) {
        rlStart.setEnabled(true);
        this.status = 0;
        this.times = times;
        rlStart.setVisibility(VISIBLE);
        btnTaskBg.clearAnimation();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        ShapeBuilder.create()
                .Radius(SizeUtils.dp2px(5))
                .Type(RECTANGLE)
                .Solid(getResources().getColor(R.color.app_color_light, null))
                .build(rlTaskBg);
        mBtnStart.setImageResource(R.drawable.btn_task_start);
        tvRemainingTime.setText(getTimewString(times));
        tvFinish.setVisibility(GONE);

    }


    public void startTask(long times) {
        rlStart.setEnabled(true);

        this.status = 1;

        rlStart.setVisibility(VISIBLE);

        this.times = times;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
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
                mBtnStart.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mBtnStart.setImageResource(R.drawable.btn_task_pause);
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
        btnTaskBg.startAnimation(rotate);
        countDownTimer.start();
        tvFinish.setVisibility(GONE);

    }

    public void pauseTask(long times) {
        rlStart.setEnabled(true);

        this.status = 2;

        btnTaskBg.clearAnimation();
        rlStart.setVisibility(VISIBLE);

        mBtnStart.setImageResource(R.drawable.btn_task_start);
        ShapeBuilder.create()
                .Radius(SizeUtils.dp2px(5))
                .Type(RECTANGLE)
                .Solid(getResources().getColor(R.color.app_color_light, null))
                .build(rlTaskBg);
        if (countDownTimer != null)
            countDownTimer.cancel();
        tvFinish.setVisibility(GONE);
        tvRemainingTime.setText("已暂停 还剩" + getTimewString(times));

    }

    public void stopTask() {

        this.status = 3;

        btnTaskBg.clearAnimation();
        rlStart.setVisibility(GONE);
        mBtnStart.setImageResource(R.drawable.btn_task_start);
        ShapeBuilder.create()
                .Radius(SizeUtils.dp2px(5))
                .Type(RECTANGLE)
                .Solid(getResources().getColor(R.color.color_finish, null))
                .build(rlTaskBg);
        if (countDownTimer != null)
            countDownTimer.cancel();
        tvRemainingTime.setText("已结束");
        tvFinish.setVisibility(VISIBLE);

    }

    public void finishTash() {
        this.status = 4;

        btnTaskBg.clearAnimation();
        rlStart.setVisibility(GONE);
        mBtnStart.setImageResource(R.drawable.btn_task_start);
        ShapeBuilder.create()
                .Radius(SizeUtils.dp2px(5))
                .Type(RECTANGLE)
                .Solid(getResources().getColor(R.color.color_finish, null))
                .build(rlTaskBg);
        if (countDownTimer != null)
            countDownTimer.cancel();
        tvRemainingTime.setText("已完成");
        tvFinish.setVisibility(GONE);
    }


    /**
     * 初始化倒计时的类
     */
    private void initCountDownTimer(final long all) {
        countDownTimer = new CountDownTimer(all, 100) {
            @Override
            public void onTick(long millisUntilFinished) {

                tvRemainingTime.setText(getTimewString(millisUntilFinished));
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
                        .build(rlTaskBg);
            }

            @Override
            public void onFinish() {
                onStatusChangeListsner.onStatusChange(3, id);

            }
        };
    }

    private String getTimewString(long t) {
        String times = "";

        long h = t / (60 * 60 * 1000);
        if (h > 0) {
            times = h + "时";

        }
        long m = (t - h * 60 * 60 * 1000) / (60 * 1000);
        if (m > 0) {
            times = times + m + "分";

        }
        long s = (t - h * 60 * 60 * 1000 - m * 60 * 1000) / 1000;
        if (s > 0) {
            times = times + s + "秒";
        }

        return times;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == rlTaskBg.getId()) {
            onStatusChangeListsner.onStatusChange((status == 0 || status == 2) ? 1 : 2, id);
        } else if (v.getId() == tvFinish.getId()) {
            onStatusChangeListsner.onStatusChange(4, id);

        }

    }

    public interface OnStatusChangeListsner {
        void onStatusChange(int status, int id);
    }
}
