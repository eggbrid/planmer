package com.kirito.planmer.user.view;

import androidx.appcompat.app.AppCompatActivity;
import com.kirito.planmer.user.R;
import kirito.peoject.baselib.mvp.BaseV;

/**
 * @auther kirito
 * @Date 2019-05-20
 * @NOTE 类说明
 */
public class UserView extends BaseV {
    public UserView(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int setViewLayout() {
        return R.layout.fragment_user;
    }

    @Override
    public void initView() {

    }
}
