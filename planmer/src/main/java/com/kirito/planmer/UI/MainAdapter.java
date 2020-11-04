package com.kirito.planmer.UI;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import kirito.peoject.baselib.thirdPart.ARouter.LibJumpHelper;
import kirito.peoject.constantlibs.UIConstant.activity.CalendarLibs;
import kirito.peoject.constantlibs.UIConstant.activity.LandLibs;
import kirito.peoject.constantlibs.UIConstant.activity.UserLibs;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther kirito
 * @Date 2019-05-20
 * @NOTE 类说明
 */
public class MainAdapter extends FragmentStateAdapter {
    List<Fragment> list=new ArrayList<>();
    public MainAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager);
        list.add(LibJumpHelper.getFragment(LandLibs.FRAGMENT_HOME ));
        list.add(LibJumpHelper.getFragment( UserLibs.FRAGMENT_USER));

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
