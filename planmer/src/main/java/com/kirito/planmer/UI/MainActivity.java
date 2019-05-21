package com.kirito.planmer.UI;

import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.viewpager2.widget.ViewPager2;
import kirito.peoject.baselib.UI.BaseActivity;

public class MainActivity extends BaseActivity<MainView> {

    private MainAdapter mainAdapter;

    @Override
    public void afterInitView(final MainView v) {
        v.commentTitleView.setOnlyTitle("首页");
        v.mAdd.setOnClickListener(this);
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        v.vp.setAdapter(mainAdapter);
        v.mBottomTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (((RadioButton) findViewById(checkedId)).isChecked()) {
                    v.vp.setCurrentItem(v.mHome.getId() == checkedId ? 0 : 1);
                    v.commentTitleView.setOnlyTitle(v.mHome.getId() == checkedId ?"首页":"用户");
                }
            }
        });
        v.vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                v.mBottomTab.check(position == 0 ? v.mHome.getId() : v.mUser.getId());
                v.commentTitleView.setOnlyTitle(position == 0  ?"首页":"用户");

            }
        });
    }

    @Override
    public void onClick(View v) {

        super.onClick(v);
        if (v.getId() == view.mAdd.getId()) {

        }
    }
}
