package com.kirito.planmer.user.activity;

import android.text.TextUtils;
import android.view.View;
import com.kirito.planmer.user.presenter.UserP;
import com.kirito.planmer.user.view.RegisterActivityView;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.util.BusUtils;
import kirito.peoject.baselib.util.ToastUtils;
import kirito.peoject.constantlibs.Message;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

/**
 * @auther kirito
 * @Date 2019-06-12
 * @NOTE 类说明
 */
public class RegisterActivity extends BaseActivity<RegisterActivityView> {
    @Override
    public void afterInitView(RegisterActivityView v) {
        super.afterInitView(v);
        view.mTvToLogin.setOnClickListener(this);
        view.mTvRegisterBtn.setOnClickListener(this);
        EventBus.getDefault().register(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == view.mTvToLogin.getId()) {
            this.finish();

        } else if (v.getId() == view.mTvRegisterBtn.getId()) {

            String userName = view.mEdtUserName.getText().toString();
            String passWord = view.mEdtPassWord.getText().toString();
            String rePassWord = view.mEdtRePassWord.getText().toString();
            String invitationCode = view.mEdtInvitationCode.getText().toString();
            if (TextUtils.isEmpty(userName)) {
                ToastUtils.showShort("请输入用户名（账户）");
                return;
            }
            if (TextUtils.isEmpty(passWord)) {
                ToastUtils.showShort("请输入密码");
                return;
            }
            if (TextUtils.isEmpty(rePassWord)) {
                ToastUtils.showShort("请再次输入密码");
                return;
            }
            if (!rePassWord.equals(passWord)){
                ToastUtils.showShort("两次输入的密码不一致哦");
                return;
            }
            if (TextUtils.isEmpty(invitationCode)){
                ToastUtils.showShort("由于是内测应用，必须输入邀请码呢");
                return;
            }
getP(UserP.class).register(this,userName,passWord,invitationCode);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Message event) {
        if (event.code == Message.REGISTER_FINISH) {
            this.finish();
        }

    }

    ;

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
