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
    public ImageView ivSetting;
    public TextView mTvName;

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
        ivSetting = findViewById(R.id.ivSetting);
        mTvName = findViewById(R.id.tvName);

    }
}
