package com.kirito.planmer.land.activity;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.ImmersionBar;
import com.kirito.planmer.land.view.widget.StartSView;

import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.constantlibs.UIConstant.activity.LandLibs;

/**
 * @Description:
 * @Author: kirito
 * @CreatTime: 2020/11/19
 * @LastModify: kirito
 * @LastModifyTime: 2020/11/19
 * @LastCheckedBy: kirito
 */
@Route(path = LandLibs.ACTIVITY_START)
public class StartActivity extends BaseActivity<StartSView> {
    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).init();
    }
}
