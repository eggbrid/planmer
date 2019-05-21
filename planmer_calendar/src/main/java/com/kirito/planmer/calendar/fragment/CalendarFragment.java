package com.kirito.planmer.calendar.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kirito.planmer.calendar.view.CalendarFragmentView;
import kirito.peoject.baselib.UI.BaseFragment;
import kirito.peoject.constantlibs.UIConstant.activity.CalendarLibs;

/**
 * @auther kirito
 * @Date 2019-05-20
 * @NOTE 日历类
 */
@Route(path = CalendarLibs.FRAGMENT_CALENDER)

public class CalendarFragment extends BaseFragment<CalendarFragmentView> {
    @Override
    public void afterInitView(CalendarFragmentView view) {

    }



}
