package com.kirito.planmer.UI;

import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.kirito.planmer.user.UserUtil;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.constantlibs.UIConstant.activity.AppLibs;
import kirito.peoject.constantlibs.UIConstant.activity.TestLibs;

/**
 * @auther kirito
 * @Date 2019-06-12
 * @NOTE 类说明
 */
@Route(path = AppLibs.ACTIVITY_SETTING)

public class SettingActivity extends BaseActivity<SettingActivityView> {
    @Override
    public void afterInitView(SettingActivityView v) {
        super.afterInitView(v);
        view.mTitle.setNormalTitle(this,"设置");
        view.mTvLoginOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId()==view.mTvLoginOut.getId()){
            UserUtil.loginOut();
        }
    }
}
