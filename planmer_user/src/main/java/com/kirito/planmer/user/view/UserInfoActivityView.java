package com.kirito.planmer.user.view;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.kirito.planmer.user.R;
import kirito.peoject.baselib.UI.widget.CommentTitleView;
import kirito.peoject.baselib.mvp.BaseV;

/**
 * @auther kirito
 * @Date 2019-06-12
 * @NOTE 类说明
 */
public class UserInfoActivityView extends BaseV {

    public CommentTitleView mTitle;
    public ImageView mIvAvatar;
    public TextView mTvNickName;
    public EditText mEdtNickName;
    public TextView mTvInvitationCode;
    public TextView mTvInvitationCodeValue;
    public TextView mTvSave;

    public UserInfoActivityView(AppCompatActivity activity) {
        super(activity);

    }

    @Override
    public int setViewLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    public void initView() {
        mTitle = findViewById(R.id.title);
        mIvAvatar = findViewById(R.id.ivAvatar);
        mTvNickName = findViewById(R.id.tvNickName);
        mEdtNickName = findViewById(R.id.edtNickName);
        mTvInvitationCode = findViewById(R.id.tvInvitationCode);
        mTvInvitationCodeValue = findViewById(R.id.tvInvitationCodeValue);
        mTvSave = findViewById(R.id.tvSave);

    }
}
