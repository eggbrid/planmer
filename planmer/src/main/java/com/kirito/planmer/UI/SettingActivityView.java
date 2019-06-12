package com.kirito.planmer.UI;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.kirito.planmer.R;
import kirito.peoject.baselib.UI.widget.CommentTitleView;
import kirito.peoject.baselib.mvp.BaseV;

/**
 * @auther kirito
 * @Date 2019-06-12
 * @NOTE 类说明
 */
public class SettingActivityView extends BaseV {
    public CommentTitleView mTitle;
    public TextView mTvClearCache;
    public TextView mTvVersion;
    public TextView mTvAboutApp;
    public TextView mTvAboutDev;
    public TextView mTvLoginOut;

    public SettingActivityView(AppCompatActivity activity) {
        super(activity);

    }

    @Override
    public int setViewLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        mTitle = findViewById(R.id.title);
        mTvClearCache = findViewById(R.id.tvClearCache);
        mTvVersion = findViewById(R.id.tvVersion);
        mTvAboutApp = findViewById(R.id.tvAboutApp);
        mTvAboutDev = findViewById(R.id.tvAboutDev);
        mTvLoginOut = findViewById(R.id.tvLoginOut);
    }
}
