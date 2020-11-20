package com.kirito.planmer.calendar.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.kirito.planmer.calendar.fragment.CalendarFragment;
import com.kirito.planmer.calendar.fragment.HomeFragment;
import com.kirito.planmer.calendar.fragment.ToDayFragment;
import kirito.peoject.baselib.thirdPart.ARouter.LibJumpHelper;
import kirito.peoject.constantlibs.UIConstant.activity.CalendarLibs;
import kirito.peoject.constantlibs.UIConstant.activity.UserLibs;

/**
 * @auther kirito
 * @Date 2019-05-20
 * @NOTE 类说明
 */
public class HomeAdapter extends FragmentStateAdapter {
    public HomeAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return position == 0 ? new ToDayFragment(): new CalendarFragment();

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
