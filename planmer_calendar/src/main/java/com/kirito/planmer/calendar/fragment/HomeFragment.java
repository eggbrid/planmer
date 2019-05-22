package com.kirito.planmer.calendar.fragment;

import androidx.viewpager2.widget.ViewPager2;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.kirito.planmer.calendar.view.HomeFragmentView;
import com.kirito.planmer.calendar.view.adapter.HomeAdapter;
import kirito.peoject.baselib.UI.BaseFragment;
import kirito.peoject.constantlibs.UIConstant.activity.CalendarLibs;

/**
 * @auther kirito
 * @Date 2019-05-21
 * @NOTE 类说明
 */
@Route(path = CalendarLibs.FRAGMENT_HOME)

public class HomeFragment extends BaseFragment<HomeFragmentView> {
    private HomeAdapter homeAdapter;

    @Override
    public void afterInitView(HomeFragmentView view) {
        homeAdapter = new HomeAdapter(getChildFragmentManager());
        view.mVp.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        view.mVp.setAdapter(homeAdapter);
    }
}
