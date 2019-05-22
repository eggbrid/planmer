package com.kirito.planmer.UI;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import kirito.peoject.baselib.thirdPart.ARouter.LibJumpHelper;
import kirito.peoject.constantlibs.UIConstant.activity.CalendarLibs;
import kirito.peoject.constantlibs.UIConstant.activity.UserLibs;

/**
 * @auther kirito
 * @Date 2019-05-20
 * @NOTE 类说明
 */
public class MainAdapter extends FragmentStateAdapter {
    public MainAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return LibJumpHelper.getFragment(position == 0 ? CalendarLibs.FRAGMENT_HOME : UserLibs.FRAGMENT_USER);

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
