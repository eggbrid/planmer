package com.kirito.planmer.calendar.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import kirito.peoject.baselib.util.LogUtils;

/**
 * @auther kirito
 * @Date 2019-06-10
 * @NOTE 类说明
 */
public class MySmartRefreshLayout extends SmartRefreshLayout {

    private int lastVisibleItemPosition;
    private int firstVisibleItemPosition;
    private float mLastY = 0;// 记录上次Y位置
    private boolean isTopToBottom = false;
    private boolean isBottomToTop = false;
    public MySmartRefreshLayout(Context context) {
        super(context);
    }

    public MySmartRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = event.getY();
                //不允许父View拦截事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float nowY = event.getY();
                if (nowY-mLastY>=0){
                    getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }else {
                    getParent().requestDisallowInterceptTouchEvent(false);

                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onTouchEvent(event);
    }


}
