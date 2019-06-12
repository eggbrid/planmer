package com.kirito.planmer.user.view;

import android.graphics.Typeface;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import com.kirito.planmer.user.R;
import kirito.peoject.baselib.mvp.BaseV;

/**
 * @auther kirito
 * @Date 2019-06-12
 * @NOTE 类说明
 */
public class RegisterActivityView extends BaseV {
    public TextView mTvRegister;
    public EditText mEdtUserName;
    public LinearLayout mLlUserName;
    public EditText mEdtPassWord;
    public LinearLayout mLlPassWord;
    public EditText mEdtRePassWord;
    public LinearLayout mLlRePassWord;
    public EditText mEdtInvitationCode;
    public LinearLayout mLlInvitationCode;
    public TextView mTvRegisterBtn;
    public TextView mTvToLogin;

    public RegisterActivityView(AppCompatActivity activity) {
        super(activity);

    }

    @Override
    public int setViewLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        mTvRegister =findViewById(R.id.tvRegister);
        mEdtUserName = findViewById(R.id.edtUserName);
        mLlUserName = findViewById(R.id.llUserName);
        mEdtPassWord = findViewById(R.id.edtPassWord);
        mLlPassWord = findViewById(R.id.llPassWord);
        mEdtRePassWord = findViewById(R.id.edtRePassWord);
        mLlRePassWord = findViewById(R.id.llRePassWord);
        mEdtInvitationCode = findViewById(R.id.edtInvitationCode);
        mLlInvitationCode = findViewById(R.id.llInvitationCode);
        mTvRegisterBtn = findViewById(R.id.tvRegisterBtn);
        mTvToLogin = findViewById(R.id.tvToLogin);
        Typeface typeface = ResourcesCompat.getFont(activity, R.font.cat);
        mTvRegister.setTypeface(typeface);
    }
}
