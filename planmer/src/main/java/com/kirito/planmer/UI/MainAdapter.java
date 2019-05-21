package com.kirito.planmer.UI;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import kirito.peoject.baselib.UI.BaseFragment;
import kirito.peoject.baselib.thirdPart.ARouter.LibJumpHelper;
import kirito.peoject.constantlibs.UIConstant.activity.CalendarLibs;
import kirito.peoject.constantlibs.UIConstant.activity.UserLibs;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther kirito
 * @Date 2019-05-20
 * @NOTE 类说明
 */
public class MainAdapter extends FragmentStateAdapter {
    private List<BaseFragment> list = new ArrayList<>();
    public MainAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager);
        list.add(LibJumpHelper.getFragment(CalendarLibs.FRAGMENT_CALENDER));
        list.add(LibJumpHelper.getFragment(UserLibs.FRAGMENT_USER));
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position > 1) {
            position = 0;
        }
        return list.get(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
