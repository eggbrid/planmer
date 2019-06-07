package com.kirito.planmer.calendar.view.widget;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.kirito.planmer.calendar.R;
import kirito.peoject.baselib.mvp.BaseV;

/**
 * @auther kirito
 * @Date 2019-05-22
 * @NOTE 类说明
 */
public class ToDayFragmentView extends BaseV {

    public TextView mTvSign;
    public TextView mTvDay;
    public ImageView mBtnStart;
    public TextView mTvTask;
    public TextView mTvTaskContent;
    public TextView mTvTime;
    public ImageView btnTaskBg;
    public RelativeLayout rlStart;
    public TextView tvRemainingTime;
    public RelativeLayout rlTaskBg;
    public TextView tvBtnSign;


    public ToDayFragmentView(AppCompatActivity activity) {
        super(activity);

    }

    @Override
    public int setViewLayout() {
        return R.layout.item_layout_home;
    }

    @Override
    public void initView() {
        rlStart = findViewById(R.id.rlStart);
        mTvSign = findViewById(R.id.tvSign);
        mTvDay = findViewById(R.id.tvDay);
        mBtnStart = findViewById(R.id.btnStart);
        mTvTask = findViewById(R.id.tvTask);
        mTvTaskContent = findViewById(R.id.tvTaskContent);
        mTvTime = findViewById(R.id.tvTime);
        btnTaskBg = findViewById(R.id.btnTaskBg);
        tvRemainingTime = findViewById(R.id.tvRemainingTime);
        rlTaskBg = findViewById(R.id.rlTaskBg);
        tvBtnSign=findViewById(R.id.tvBtnSign);
    }
}
