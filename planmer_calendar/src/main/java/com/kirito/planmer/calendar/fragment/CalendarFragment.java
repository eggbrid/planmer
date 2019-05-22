package com.kirito.planmer.calendar.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.kirito.planmer.calendar.view.CalendarFragmentView;
import com.kirito.planmer.calendar.view.adapter.HomeCalendarPageAdapter;
import kirito.peoject.baselib.UI.BaseFragment;
import kirito.peoject.constantlibs.UIConstant.activity.CalendarLibs;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther kirito
 * @Date 2019-05-20
 * @NOTE 日历类
 */

public class CalendarFragment extends BaseFragment<CalendarFragmentView> {
    public HomeCalendarPageAdapter homeCalendarPageAdapter;

    @Override
    public void afterInitView(CalendarFragmentView view) {
        homeCalendarPageAdapter = new HomeCalendarPageAdapter();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            list.add("第" + i + "条数据");

        }
        view.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeCalendarPageAdapter.setList(list);
        homeCalendarPageAdapter.notifyDataSetChanged();
        view.recyclerView.setAdapter(homeCalendarPageAdapter);
    }


}
