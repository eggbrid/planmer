package com.kirito.planmer.user.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kirito.planmer.user.view.UserView;
import kirito.peoject.baselib.UI.BaseFragment;
import kirito.peoject.constantlibs.UIConstant.activity.UserLibs;

/**
 * @auther kirito
 * @Date 2019-05-20
 * @NOTE 类说明
 */
@Route(path = UserLibs.FRAGMENT_USER)
public class UserFragment extends BaseFragment<UserView> {
    @Override
    public void afterInitView(UserView view) {

    }
}
