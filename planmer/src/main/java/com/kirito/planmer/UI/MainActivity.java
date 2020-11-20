package com.kirito.planmer.UI;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.ImmersionBar;

import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.UI.BaseFragment;
import kirito.peoject.baselib.thirdPart.ARouter.LibJumpHelper;
import kirito.peoject.baselib.util.ToastUtils;
import kirito.peoject.constantlibs.UIConstant.Main;
import kirito.peoject.constantlibs.UIConstant.activity.LandLibs;

@Route(path = Main.ACTIVITY_MAIN)

public class MainActivity extends BaseActivity<MainView> {

    private MainAdapter mainAdapter;

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).init();
    }

    @Override
    public void afterInitView(final MainView v) {


        v.start.setOnClickListener(this);
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        v.vp.setAdapter(mainAdapter);
        v.mBottomTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (((RadioButton) findViewById(checkedId)).isChecked()) {
                    v.vp.setCurrentItem(v.mHome.getId() == checkedId ? 0 : 1);
                    if (getSupportFragmentManager().getFragments() != null && getSupportFragmentManager().getFragments().size() > (v.mHome.getId() == checkedId ? 0 : 1) + 1) {
                        ((BaseFragment) getSupportFragmentManager().getFragments().get((v.mHome.getId() == checkedId ? 0 : 1) + 1)).initImmersionBar();

                    }
                }
            }
        });

        v.vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                v.mBottomTab.check(position == 0 ? v.mHome.getId() : v.mUser.getId());


            }
        });

    }

    @Override
    public void onClick(View v) {

        super.onClick(v);
        if (v.getId() == view.start.getId()) {
            LibJumpHelper.startActivity(LandLibs.ACTIVITY_START);
        }
    }

    long times = 0;

    @Override
    public void onBackClick() {
        if (times != 0) {
            if (System.currentTimeMillis() - times <= 1000) {
                this.finish();
                System.exit(0);
            } else {
                times = 0;
            }
        } else {
            times = System.currentTimeMillis();
            ToastUtils.showShort("再次按下返回退出");
        }
    }
}
