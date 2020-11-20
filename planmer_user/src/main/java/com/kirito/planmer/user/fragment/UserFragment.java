package com.kirito.planmer.user.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.kirito.planmer.user.UserUtil;
import com.kirito.planmer.user.activity.UserInfoActivity;
import com.kirito.planmer.user.model.AllUserModel;
import com.kirito.planmer.user.presenter.UserP;
import com.kirito.planmer.user.view.UserView;

import kirito.peoject.baselib.UI.BaseFragment;
import kirito.peoject.baselib.thirdPart.ARouter.LibJumpHelper;
import kirito.peoject.baselib.util.DensityUtil;
import kirito.peoject.baselib.util.IntentUtil;
import kirito.peoject.baselib.util.LiuHaiUtil;
import kirito.peoject.constantlibs.Message;
import kirito.peoject.constantlibs.UIConstant.activity.AppLibs;
import kirito.peoject.constantlibs.UIConstant.activity.UserLibs;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @auther kirito
 * @Date 2019-05-20
 * @NOTE 类说明
 */
@Route(path = UserLibs.FRAGMENT_USER)
public class UserFragment extends BaseFragment<UserView> implements View.OnClickListener {
    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).init();
    }

    @Override
    public void afterInitView(UserView view) {
        view.mIvAvatar.setOnClickListener(this);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.mIvAvatar.getLayoutParams();
        marginLayoutParams.setMargins(0, LiuHaiUtil.getTrueStatusBar(getContext()) + DensityUtil.dp2px(getContext(), 20), 0, 0);
        view.mIvAvatar.setLayoutParams(marginLayoutParams);
        loadCache();
        getP(UserP.class).getUser(view);
        EventBus.getDefault().register(this);

        view.tvLoginOut.setOnClickListener(this);
    }


    public void loadCache() {
        AllUserModel allUserModel = UserUtil.getUserInfo();
        if (allUserModel != null && allUserModel.getUserInfo() != null) {
            RequestOptions mRequestOptions = RequestOptions.circleCropTransform();
            view.mTvName.setText(allUserModel.getUserInfo().nickName);
            Glide.with(view.mIvAvatar.getContext())
                    .load(allUserModel.getUserInfo().getAvatar())
                    .apply(mRequestOptions)
                    .into(view.mIvAvatar);
        }
    }

    @Subscribe()
    public void onMessageEvent(Message event) {
        if (event.code == Message.USER_INFO_UPDATED) {
            loadCache();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == view.mIvAvatar.getId()) {
            IntentUtil.startActivity(activity, UserInfoActivity.class);
        } else if (v.getId() == view.tvLoginOut.getId()) {
            UserUtil.loginOut();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
