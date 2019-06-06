package com.kirito.planmer.calendar.view.widget.task;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.kirito.planmer.calendar.R;
import kirito.peoject.baselib.UI.widget.CommentTitleView;
import kirito.peoject.baselib.mvp.BaseV;
import kirito.peoject.baselib.util.TimeUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther kirito
 * @Date 2019-06-06
 * @NOTE 类说明
 */
public class AddTaskActivityView extends BaseV {


    public TimePickerView startTime;
    public OptionsPickerView day;
    public OptionsPickerView dDtime;

    public List<String> hour = new ArrayList<>();
    public List<Integer> hourValue = new ArrayList<>();

    public List<String> minute = new ArrayList<>();
    public List<Integer> minuteValue = new ArrayList<>();


    public List<String> dayTime = new ArrayList<>();
    public List<Integer> dayTimeValue = new ArrayList<>();
    public AddTaskListener addTaskListener;
    public CommentTitleView mTitle;
    public EditText mEdtTitle;
    public TextView mTvStartTime;
    public TextView mTvDay;
    public TextView mTvDTime;
    public TextView mTvContent;
    public EditText mEdtContent;
    public TextView tvSubmit;

    public AddTaskActivityView(AppCompatActivity activity) {
        super(activity);

    }

    @Override
    public int setViewLayout() {
        return R.layout.act_add_task;
    }

    @Override
    public void initView() {
        mTitle = findViewById(R.id.title);
        mEdtTitle = findViewById(R.id.edtTitle);
        mTvStartTime = findViewById(R.id.tvStartTime);
        mTvDay = findViewById(R.id.tvDay);
        mTvDTime = findViewById(R.id.tvDTime);
        mTvContent = findViewById(R.id.tvContent);
        mEdtContent = findViewById(R.id.edtContent);
        tvSubmit=findViewById(R.id.tvSubmit);
        for (int i = 0; i < 24; i++) {
            hour.add(i + "小时");
            hourValue.add(i);
        }
        for (int i = 1; i < 60; i++) {
            minute.add(i + "分钟");
            minuteValue.add(i);

        }
        for (int i = 1; i < 8; i++) {
            dayTime.add(i + "天");
            dayTimeValue.add(i);

        }
        startTime = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                addTaskListener.onStartTimeSelect(date.getTime());
                mTvStartTime.setText(TimeUtils.date2String(date, new SimpleDateFormat("yyyy-MM-dd")));

            }
        }).setTextColorCenter(activity.getResources().getColor(R.color.app_color, null))
                .setTextColorOut(activity.getResources().getColor(R.color.app_text_color, null))
                .setTitleColor(activity.getResources().getColor(R.color.app_color, null))//标题文字颜色
                .setSubmitColor(activity.getResources().getColor(R.color.app_color, null))//确定按钮文字颜色
                .setCancelColor(activity.getResources().getColor(R.color.app_color, null))//取消按钮文字颜色
                .setContentTextSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .build();

        day = new OptionsPickerBuilder(activity, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                addTaskListener.onDaySelect(dayTimeValue.get(options1));
                mTvDay.setText(dayTime.get(options1));
            }
        }).setTextColorCenter(activity.getResources().getColor(R.color.app_color, null))
                .setTextColorOut(activity.getResources().getColor(R.color.app_text_color, null))
                .setTitleColor(activity.getResources().getColor(R.color.app_color, null))//标题文字颜色
                .setSubmitColor(activity.getResources().getColor(R.color.app_color, null))//确定按钮文字颜色
                .setCancelColor(activity.getResources().getColor(R.color.app_color, null))//取消按钮文字颜色
                .setContentTextSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .build();
        day.setNPicker(dayTime, null, null);


        dDtime = new OptionsPickerBuilder(activity, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                long h = hourValue.get(options1) * 60;
                long m = minuteValue.get(option2);
                addTaskListener.onDtimeSelect(h + m);
                mTvDTime.setText(hour.get(options1)+" "+minute.get(option2));
            }
        }).setTextColorCenter(activity.getResources().getColor(R.color.app_color, null))
                .setTextColorOut(activity.getResources().getColor(R.color.app_text_color, null))
                .setTitleColor(activity.getResources().getColor(R.color.app_color, null))//标题文字颜色
                .setSubmitColor(activity.getResources().getColor(R.color.app_color, null))//确定按钮文字颜色
                .setCancelColor(activity.getResources().getColor(R.color.app_color, null))//取消按钮文字颜色
                .setContentTextSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .build();
        dDtime.setNPicker(hour, minute, null);

    }


    public void setAddTaskListener(AddTaskListener addTaskListener) {
        this.addTaskListener = addTaskListener;
    }

    public interface AddTaskListener {
        void onStartTimeSelect(long time);

        void onDaySelect(int time);

        void onDtimeSelect(long time);
    }
}
