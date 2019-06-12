package com.kirito.planmer.user.activity;

import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.kirito.planmer.user.presenter.UserP;
import com.kirito.planmer.user.view.LoginActivityView;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.util.ActivityUtils;
import kirito.peoject.baselib.util.IntentUtil;
import kirito.peoject.constantlibs.Message;
import kirito.peoject.constantlibs.UIConstant.activity.UserLibs;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        ActivityUtils.finishOtherActivities(LoginActivity.class);
        v.tvLoginBtn.setOnClickListener(this);
        v.tvToRegister.setOnClickListener(this);
        EventBus.getDefault().register(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == view.tvLoginBtn.getId()) {
            getP(UserP.class).login(this);
        }else if(v.getId()==view.tvToRegister.getId()){
            IntentUtil.startActivity(this,RegisterActivity.class);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Message event) {
        if (event.code==Message.REGISTER_FINISH){
            this.finish();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
