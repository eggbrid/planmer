package com.kirito.planmer.calendar.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.kirito.planmer.calendar.R;
import com.kirito.planmer.calendar.view.adapter.HomeCalendarPageAdapter;
import kirito.peoject.baselib.mvp.BaseV;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther kirito
 * @Date 2019-05-20
 * @NOTE 类说明
 */
public class CalendarFragmentView extends BaseV {
    public RecyclerView recyclerView;

    public CalendarFragmentView(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int setViewLayout() {
        return R.layout.fragment_calendar;
    }

    @Override
    public void initView() {

            recyclerView = findViewById(R.id.recyclerView);

    }
}
