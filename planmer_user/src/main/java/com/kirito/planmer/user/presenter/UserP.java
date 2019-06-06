package com.kirito.planmer.user.presenter;

import android.app.Activity;
import com.kirito.planmer.user.activity.LoginActivity;
import com.kirito.planmer.user.model.User;
import com.kirito.planmer.user.server.UserServer;
import com.kirito.planmer.user.view.LoginActivityView;
import io.reactivex.Observable;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.mvp.BaseP;
import kirito.peoject.baselib.thirdPart.ARouter.LibJumpHelper;
import kirito.peoject.baselib.thirdPart.Retrofit.NetCallBack;
import kirito.peoject.baselib.util.SPUtils;
import kirito.peoject.baselib.util.ShellUtils;
import kirito.peoject.baselib.util.ToastUtils;
import kirito.peoject.constantlibs.UIConstant.Main;

/**
 * @auther kirito
 * @Date 2019-05-24
 * @NOTE 类说明
 */
public class UserP extends BaseP<UserServer> {
    public Observable<User> login(final BaseActivity<LoginActivityView> activity) {
        final String userName=activity.view.edtUserName.getText().toString();
        String passWord=activity.view.edtPassWord.getText().toString();

activity.view.showLoading("登录中...");
        return request(getService().login(userName,passWord),    new NetCallBack<User>() {
            @Override
            public void onGetData(User user) {

                SPUtils.getInstance().put("token",user.getToken());
                activity.view.dismissLoading();

                LibJumpHelper.startActivity(Main.ACTIVITY_MAIN);
                activity.finish();
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onFailure(String message) {
                activity.view.dismissLoading();
                ToastUtils.showShort(message);
            }
        });
    }
}
