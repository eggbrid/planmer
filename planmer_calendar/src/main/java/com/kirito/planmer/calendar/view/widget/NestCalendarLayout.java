package com.kirito.planmer.calendar.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.kirito.planmer.calendar.view.widget.calendarview.CalendarLayout;

/**
 * @auther kirito
 * @Date 2019-05-22
 * @NOTE 类说明
 */
public class NestCalendarLayout extends CalendarLayout {

    private float mLastY = 0;// 记录上次Y位置

    public NestCalendarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


       return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(ev);
    }
}
