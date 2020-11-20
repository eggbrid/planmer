package com.kirito.planmer.calendar.view.widget;


import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.util.SmartUtil;

/**
 * @Description:
 * @Author: kirito
 * @CreatTime: 2020/11/19
 * @LastModify: kirito
 * @LastModifyTime: 2020/11/19
 * @LastCheckedBy: kirito
 */
public class MyBezierCircleHeader extends BezierCircleHeader {
    public MyBezierCircleHeader(Context context) {
        this(context, null);
    }

    public MyBezierCircleHeader(Context context, AttributeSet attrs) {
        super(context, attrs);

        mSpinnerStyle = SpinnerStyle.FixedBehind;
        final View thisView = this;
        thisView.setMinimumHeight(SmartUtil.dp2px(100));
        mBackPaint = new Paint();
        mBackPaint.setColor(0xff000000);
        mBackPaint.setAntiAlias(true);
        mFrontPaint = new Paint();
        mFrontPaint.setColor(0xffffffff);
        mFrontPaint.setAntiAlias(true);
        mOuterPaint = new Paint();
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setColor(0xffffffff);
        mOuterPaint.setStyle(Paint.Style.STROKE);
        mOuterPaint.setStrokeWidth(SmartUtil.dp2px(2f));
        mPath = new Path();
    }
}
