package com.kirito.planmer.land.view.widget;


import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.kirito.planmer.land.R;
import com.kirito.planmer.land.view.OpenGLView;

import kirito.peoject.baselib.mvp.BaseV;
import kirito.peoject.baselib.util.DensityUtil;
import kirito.peoject.baselib.util.LiuHaiUtil;

/**
 * @Description:
 * @Author: kirito
 * @CreatTime: 2020/11/19
 * @LastModify: kirito
 * @LastModifyTime: 2020/11/19
 * @LastCheckedBy: kirito
 */
public class StartSView extends BaseV {
    private RelativeLayout rlBg;
    private ImageView ivClose;

    public StartSView(AppCompatActivity activity) {
        super(activity);
    }


    @Override
    public int setViewLayout() {
        return R.layout.act_start;
    }

    @Override
    public void initView() {
        rlBg = (RelativeLayout) findViewById(R.id.rlBg);
        ivClose = (ImageView) findViewById(R.id.ivClose);
        OpenGLView openGLView = new OpenGLView(activity);
        RelativeLayout rlBg = findViewById(R.id.rlBg);
        rlBg.addView(openGLView);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

        rlBg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) ivClose.getLayoutParams();
        marginLayoutParams.setMargins(DensityUtil.dp2px(activity, 10), LiuHaiUtil.getTrueStatusBar(activity), 0, 0);
        ivClose.setLayoutParams(marginLayoutParams);
    }
}
