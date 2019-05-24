package com.kirito.planmer.user.view;

import androidx.appcompat.app.AppCompatActivity;
import com.kirito.planmer.user.R;
import kirito.peoject.baselib.mvp.BaseV;

/**
 * @auther kirito
 * @Date 2019-05-24
 * @NOTE 类说明
 */
public class LoginActivityView extends BaseV {
    public LoginActivityView(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int setViewLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }
}
