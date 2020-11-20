package com.kirito.planmer.user.view;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kirito.planmer.user.R;

import kirito.peoject.baselib.mvp.BaseV;

/**
 * @auther kirito
 * @Date 2019-05-20
 * @NOTE 类说明
 */
public class UserView extends BaseV {
    public ImageView mIvAvatar;
    public TextView mTvName;
    public ImageView ivAvatar;
    public TextView tvName;
    public TextView tvClearCache;
    public TextView tvVersion;
    public TextView tvAboutApp;
    public TextView tvAboutDev;
    public TextView tvLoginOut;

    public UserView(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int setViewLayout() {
        return R.layout.fragment_user;
    }

    @Override
    public void initView() {
        barColor = R.color.app_color_light;
        mIvAvatar = findViewById(R.id.ivAvatar);
        mTvName = findViewById(R.id.tvName);
        tvClearCache = (TextView) findViewById(R.id.tvClearCache);
        tvVersion = (TextView) findViewById(R.id.tvVersion);
        tvAboutApp = (TextView) findViewById(R.id.tvAboutApp);
        tvAboutDev = (TextView) findViewById(R.id.tvAboutDev);
        tvLoginOut = (TextView) findViewById(R.id.tvLoginOut);
    }
}
