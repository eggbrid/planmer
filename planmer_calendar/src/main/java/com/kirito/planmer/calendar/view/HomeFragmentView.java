package com.kirito.planmer.calendar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.kirito.planmer.calendar.R;

import kirito.peoject.baselib.UI.widget.CommentTitleView;
import kirito.peoject.baselib.mvp.BaseV;

/**
 * @auther kirito
 * @Date 2019-05-21
 * @NOTE 类说明
 */
public class HomeFragmentView extends BaseV {
    public ViewPager2 mVp;

    public HomeFragmentView(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int setViewLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        mVp = findViewById(R.id.vp);
    }
}
