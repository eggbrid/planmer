package com.kirito.planmer.calendar.view;

import androidx.appcompat.app.AppCompatActivity;
import com.kirito.planmer.calendar.R;
import kirito.peoject.baselib.mvp.BaseV;

/**
 * @auther kirito
 * @Date 2019-05-20
 * @NOTE 类说明
 */
public class CalendarFragmentView extends BaseV {
    public CalendarFragmentView(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int setViewLayout() {
        return R.layout.fragment_calendar;
    }

    @Override
    public void initView() {

    }
}
