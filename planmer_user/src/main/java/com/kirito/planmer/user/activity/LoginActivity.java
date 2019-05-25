package com.kirito.planmer.user.activity;

import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.kirito.planmer.user.model.User;
import com.kirito.planmer.user.presenter.UserP;
import com.kirito.planmer.user.view.LoginActivityView;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.thirdPart.ARouter.LibJumpHelper;
import kirito.peoject.baselib.thirdPart.Retrofit.NetCallBack;
import kirito.peoject.baselib.util.ToastUtils;
import kirito.peoject.constantlibs.UIConstant.Main;
import kirito.peoject.constantlibs.UIConstant.activity.UserLibs;

/**
 * @auther kirito
 * @Date 2019-05-24
 * @NOTE 类说明
 */
@Route(path = UserLibs.ACTIVITY_LOPGIN)

public class LoginActivity extends BaseActivity<LoginActivityView> {
    @Override
    public void afterInitView(LoginActivityView v) {
        super.afterInitView(v);
        v.tvLoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==view.tvLoginBtn.getId()){
            getP(UserP.class).login(this);
        }
    }
}
